package com.uca.pncparcialfinalrestaurante.order.controller;

import com.uca.pncparcialfinalrestaurante.order.domain.dto.OrderRequest;
import com.uca.pncparcialfinalrestaurante.order.domain.dto.OrderResponse;
import com.uca.pncparcialfinalrestaurante.order.service.OrderService;
import com.uca.pncparcialfinalrestaurante.user.domain.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<OrderResponse> createOrder(
            @Valid @RequestBody OrderRequest request,
            @AuthenticationPrincipal User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(request, user));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @orderSecurity.isOwner(authentication, #id)")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<OrderResponse>> getAllOrders(@AuthenticationPrincipal User user) {
        String roleName = user.getRole().getName().name();

        if (roleName.equals("ADMIN")) {
            return ResponseEntity.ok(orderService.getAllOrders());
        }

        if (roleName.equals("RESTAURANT")) {
            Long restaurantId = user.getRestaurant() != null ? user.getRestaurant().getId() : null;
            if (restaurantId == null) {
                return ResponseEntity.ok(List.of());
            }
            return ResponseEntity.ok(orderService.getOrdersByRestaurantId(restaurantId));
        }

        // Default to CLIENT, return only their own orders
        return ResponseEntity.ok(orderService.getOrdersByClientId(user.getId()));
    }

    @PatchMapping("/{id}/confirm")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('RESTAURANT') and @orderSecurity.isOwner(authentication, #id))")
    public ResponseEntity<OrderResponse> confirmOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.confirmOrder(id));
    }

    @PatchMapping("/{id}/cancel")
    @PreAuthorize("hasRole('ADMIN') or @orderSecurity.isOwner(authentication, #id)")
    public ResponseEntity<OrderResponse> cancelOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.cancelOrder(id));
    }
}

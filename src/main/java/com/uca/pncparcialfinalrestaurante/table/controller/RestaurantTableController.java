package com.uca.pncparcialfinalrestaurante.table.controller;

import com.uca.pncparcialfinalrestaurante.table.domain.dto.RestaurantTableRequest;
import com.uca.pncparcialfinalrestaurante.table.domain.dto.RestaurantTableResponse;
import com.uca.pncparcialfinalrestaurante.table.service.RestaurantTableService;
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
@RequestMapping("api/tables")
@RequiredArgsConstructor
public class RestaurantTableController {

    private final RestaurantTableService tableService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or (hasRole('RESTAURANT') and @restaurantSecurity.isOwner(authentication, #request.restaurant.id))")
    public ResponseEntity<RestaurantTableResponse> createTable(@Valid @RequestBody RestaurantTableRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tableService.createTable(request));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @tableSecurity.isOwner(authentication, #id)")
    public ResponseEntity<RestaurantTableResponse> getTableById(@PathVariable Long id) {
        return ResponseEntity.ok(tableService.getTableById(id));
    }

    @GetMapping
    public ResponseEntity<List<RestaurantTableResponse>> getAllTables(@AuthenticationPrincipal User user) {
        // If RESTAURANT role (turn manager), retrieve only tables of their restaurant
        if (user.getRole().getName().name().equals("RESTAURANT")) {
            Long restaurantId = user.getRestaurant() != null ? user.getRestaurant().getId() : null;
            if (restaurantId == null) {
                return ResponseEntity.ok(List.of());
            }
            return ResponseEntity.ok(tableService.getTablesByRestaurantId(restaurantId));
        }
        // Admin or Client can see all tables
        return ResponseEntity.ok(tableService.getAllTables());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @tableSecurity.isOwner(authentication, #id)")
    public ResponseEntity<RestaurantTableResponse> updateTable(
            @PathVariable Long id,
            @Valid @RequestBody RestaurantTableRequest request) {
        return ResponseEntity.ok(tableService.updateTable(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @tableSecurity.isOwner(authentication, #id)")
    public ResponseEntity<Void> deleteTable(@PathVariable Long id) {
        tableService.deleteTable(id);
        return ResponseEntity.noContent().build();
    }
}

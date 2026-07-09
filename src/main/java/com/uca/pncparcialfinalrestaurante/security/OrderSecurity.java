package com.uca.pncparcialfinalrestaurante.security;

import com.uca.pncparcialfinalrestaurante.exceptions.ResourceNotFoundException;
import com.uca.pncparcialfinalrestaurante.order.domain.entity.Order;
import com.uca.pncparcialfinalrestaurante.order.domain.repository.OrderRepository;
import com.uca.pncparcialfinalrestaurante.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("orderSecurity")
@RequiredArgsConstructor
public class OrderSecurity {

    private final OrderRepository orderRepository;

    public boolean isOwner(Authentication authentication, Long id) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        User currentUser;
        try {
            currentUser = (User) authentication.getPrincipal();
        } catch (ClassCastException e) {
            return false;
        }

        // Admins can do anything
        if (currentUser.getRole().getName().name().equals("ADMIN")) {
            return true;
        }

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));

        // If Client, check if they are the owner of the order
        if (currentUser.getRole().getName().name().equals("CLIENT")) {
            return order.getClient() != null && order.getClient().getId().equals(currentUser.getId());
        }

        // If Encargado (RESTAURANT), check if the order belongs to their sucursal
        if (currentUser.getRole().getName().name().equals("RESTAURANT")) {
            return currentUser.getRestaurant() != null &&
                   order.getTable() != null &&
                   order.getTable().getRestaurant() != null &&
                   order.getTable().getRestaurant().getId().equals(currentUser.getRestaurant().getId());
        }

        return false;
    }
}

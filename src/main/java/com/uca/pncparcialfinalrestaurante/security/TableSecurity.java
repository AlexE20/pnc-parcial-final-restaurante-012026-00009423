package com.uca.pncparcialfinalrestaurante.security;

import com.uca.pncparcialfinalrestaurante.exceptions.ResourceNotFoundException;
import com.uca.pncparcialfinalrestaurante.table.domain.entity.RestaurantTable;
import com.uca.pncparcialfinalrestaurante.table.domain.repository.RestaurantTableRepository;
import com.uca.pncparcialfinalrestaurante.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("tableSecurity")
@RequiredArgsConstructor
public class TableSecurity {

    private final RestaurantTableRepository tableRepository;

    public boolean isOwner(Authentication authentication, Long tableId) {
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

        // If Encargado (RESTAURANT), check if the table belongs to their restaurant sucursal
        if (currentUser.getRole().getName().name().equals("RESTAURANT")) {
            RestaurantTable table = tableRepository.findById(tableId)
                    .orElseThrow(() -> new ResourceNotFoundException("Table not found with id " + tableId));

            return currentUser.getRestaurant() != null &&
                   table.getRestaurant() != null &&
                   table.getRestaurant().getId().equals(currentUser.getRestaurant().getId());
        }

        return false;
    }
}

package com.uca.pncparcialfinalrestaurante.security;

import com.uca.pncparcialfinalrestaurante.user.domain.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("restaurantSecurity")
public class RestaurantSecurity {

    public boolean isOwner(Authentication authentication, Long restaurantId) {
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

        // If Encargado (RESTAURANT), verify if they belong to this restaurant
        if (currentUser.getRole().getName().name().equals("RESTAURANT")) {
            return currentUser.getRestaurant() != null &&
                   currentUser.getRestaurant().getId().equals(restaurantId);
        }

        return false;
    }
}

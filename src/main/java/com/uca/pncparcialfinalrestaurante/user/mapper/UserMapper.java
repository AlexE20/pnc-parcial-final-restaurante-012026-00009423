package com.uca.pncparcialfinalrestaurante.user.mapper;

import com.uca.pncparcialfinalrestaurante.user.domain.dto.UserRequest;
import com.uca.pncparcialfinalrestaurante.user.domain.dto.UserResponse;
import com.uca.pncparcialfinalrestaurante.user.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserRequest request) {
        return User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .role(request.getRole())
                .restaurant(request.getRestaurant())
                .build();
    }

    public UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .role(user.getRole() != null ? user.getRole().getName().name() : null)
                .restaurantId(user.getRestaurant() != null ? user.getRestaurant().getId() : null)
                .restaurantName(user.getRestaurant() != null ? user.getRestaurant().getName() : null)
                .build();
    }
}

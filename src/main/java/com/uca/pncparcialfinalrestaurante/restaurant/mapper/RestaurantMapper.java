package com.uca.pncparcialfinalrestaurante.restaurant.mapper;

import com.uca.pncparcialfinalrestaurante.restaurant.domain.dto.RestaurantRequest;
import com.uca.pncparcialfinalrestaurante.restaurant.domain.dto.RestaurantResponse;
import com.uca.pncparcialfinalrestaurante.restaurant.domain.entity.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class RestaurantMapper {

    public Restaurant toEntity(RestaurantRequest request) {
        if (request == null) {
            return null;
        }
        return Restaurant.builder()
                .name(request.getName())
                .address(request.getAddress())
                .build();
    }

    public RestaurantResponse toResponse(Restaurant restaurant) {
        if (restaurant == null) {
            return null;
        }
        return RestaurantResponse.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .build();
    }
}

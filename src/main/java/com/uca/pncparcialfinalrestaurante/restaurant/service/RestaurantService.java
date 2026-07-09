package com.uca.pncparcialfinalrestaurante.restaurant.service;

import com.uca.pncparcialfinalrestaurante.restaurant.domain.dto.RestaurantRequest;
import com.uca.pncparcialfinalrestaurante.restaurant.domain.dto.RestaurantResponse;

import java.util.List;

public interface RestaurantService {
    RestaurantResponse createRestaurant(RestaurantRequest request);
    RestaurantResponse getRestaurantById(Long id);
    List<RestaurantResponse> getAllRestaurants();
    RestaurantResponse updateRestaurant(Long id, RestaurantRequest request);
    void deleteRestaurant(Long id);
}

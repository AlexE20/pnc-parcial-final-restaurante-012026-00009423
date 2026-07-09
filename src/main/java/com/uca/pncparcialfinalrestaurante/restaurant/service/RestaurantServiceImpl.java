package com.uca.pncparcialfinalrestaurante.restaurant.service;

import com.uca.pncparcialfinalrestaurante.exceptions.ResourceNotFoundException;
import com.uca.pncparcialfinalrestaurante.restaurant.domain.dto.RestaurantRequest;
import com.uca.pncparcialfinalrestaurante.restaurant.domain.dto.RestaurantResponse;
import com.uca.pncparcialfinalrestaurante.restaurant.domain.entity.Restaurant;
import com.uca.pncparcialfinalrestaurante.restaurant.domain.repository.RestaurantRepository;
import com.uca.pncparcialfinalrestaurante.restaurant.mapper.RestaurantMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    @Override
    public RestaurantResponse createRestaurant(RestaurantRequest request) {
        if (restaurantRepository.findAll().stream().anyMatch(r -> r.getName().equalsIgnoreCase(request.getName()))) {
            throw new IllegalArgumentException("Restaurant with this name already exists");
        }
        Restaurant restaurant = restaurantMapper.toEntity(request);
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        return restaurantMapper.toResponse(savedRestaurant);
    }

    @Override
    public RestaurantResponse getRestaurantById(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id: " + id));
        return restaurantMapper.toResponse(restaurant);
    }

    @Override
    public List<RestaurantResponse> getAllRestaurants() {
        return restaurantRepository.findAll().stream()
                .map(restaurantMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RestaurantResponse updateRestaurant(Long id, RestaurantRequest request) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id: " + id));

        restaurant.setName(request.getName());
        restaurant.setAddress(request.getAddress());

        Restaurant updatedRestaurant = restaurantRepository.save(restaurant);
        return restaurantMapper.toResponse(updatedRestaurant);
    }

    @Override
    public void deleteRestaurant(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id: " + id));
        restaurantRepository.delete(restaurant);
    }
}

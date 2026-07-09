package com.uca.pncparcialfinalrestaurante.table.service;

import com.uca.pncparcialfinalrestaurante.table.domain.dto.RestaurantTableRequest;
import com.uca.pncparcialfinalrestaurante.table.domain.dto.RestaurantTableResponse;

import java.util.List;

public interface RestaurantTableService {
    RestaurantTableResponse createTable(RestaurantTableRequest request);
    RestaurantTableResponse getTableById(Long id);
    List<RestaurantTableResponse> getAllTables();
    List<RestaurantTableResponse> getTablesByRestaurantId(Long restaurantId);
    RestaurantTableResponse updateTable(Long id, RestaurantTableRequest request);
    void deleteTable(Long id);
}

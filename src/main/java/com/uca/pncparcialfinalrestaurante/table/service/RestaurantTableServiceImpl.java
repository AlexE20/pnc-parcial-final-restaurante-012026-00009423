package com.uca.pncparcialfinalrestaurante.table.service;

import com.uca.pncparcialfinalrestaurante.exceptions.ResourceNotFoundException;
import com.uca.pncparcialfinalrestaurante.restaurant.domain.entity.Restaurant;
import com.uca.pncparcialfinalrestaurante.restaurant.domain.repository.RestaurantRepository;
import com.uca.pncparcialfinalrestaurante.table.domain.dto.RestaurantTableRequest;
import com.uca.pncparcialfinalrestaurante.table.domain.dto.RestaurantTableResponse;
import com.uca.pncparcialfinalrestaurante.table.domain.entity.RestaurantTable;
import com.uca.pncparcialfinalrestaurante.table.domain.entity.TableStatus;
import com.uca.pncparcialfinalrestaurante.table.domain.repository.RestaurantTableRepository;
import com.uca.pncparcialfinalrestaurante.table.mapper.RestaurantTableMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantTableServiceImpl implements RestaurantTableService {

    private final RestaurantTableRepository tableRepository;
    private final RestaurantRepository restaurantRepository;
    private final RestaurantTableMapper tableMapper;

    @Override
    public RestaurantTableResponse createTable(RestaurantTableRequest request) {
        Restaurant restaurant = restaurantRepository.findById(request.getRestaurant().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id: " + request.getRestaurant().getId()));

        RestaurantTable table = tableMapper.toEntity(request);
        table.setRestaurant(restaurant);
        RestaurantTable savedTable = tableRepository.save(table);
        return tableMapper.toResponse(savedTable);
    }

    @Override
    public RestaurantTableResponse getTableById(Long id) {
        RestaurantTable table = tableRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Table not found with id: " + id));
        return tableMapper.toResponse(table);
    }

    @Override
    public List<RestaurantTableResponse> getAllTables() {
        return tableRepository.findAll().stream()
                .map(tableMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<RestaurantTableResponse> getTablesByRestaurantId(Long restaurantId) {
        return tableRepository.findByRestaurantId(restaurantId).stream()
                .map(tableMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RestaurantTableResponse updateTable(Long id, RestaurantTableRequest request) {
        RestaurantTable table = tableRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Table not found with id: " + id));

        Restaurant restaurant = restaurantRepository.findById(request.getRestaurant().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id: " + request.getRestaurant().getId()));

        table.setRestaurant(restaurant);
        table.setCapacity(request.getCapacity());
        table.setStatus(TableStatus.valueOf(request.getStatus().toUpperCase()));

        RestaurantTable updatedTable = tableRepository.save(table);
        return tableMapper.toResponse(updatedTable);
    }

    @Override
    public void deleteTable(Long id) {
        RestaurantTable table = tableRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Table not found with id: " + id));
        tableRepository.delete(table);
    }
}

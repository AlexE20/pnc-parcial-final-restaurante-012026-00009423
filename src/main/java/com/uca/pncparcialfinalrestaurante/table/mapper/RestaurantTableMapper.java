package com.uca.pncparcialfinalrestaurante.table.mapper;

import com.uca.pncparcialfinalrestaurante.table.domain.dto.RestaurantTableRequest;
import com.uca.pncparcialfinalrestaurante.table.domain.dto.RestaurantTableResponse;
import com.uca.pncparcialfinalrestaurante.table.domain.entity.RestaurantTable;
import com.uca.pncparcialfinalrestaurante.table.domain.entity.TableStatus;
import org.springframework.stereotype.Component;

@Component
public class RestaurantTableMapper {

    public RestaurantTable toEntity(RestaurantTableRequest request) {
        if (request == null) {
            return null;
        }
        return RestaurantTable.builder()
                .restaurant(request.getRestaurant())
                .capacity(request.getCapacity())
                .status(TableStatus.valueOf(request.getStatus().toUpperCase()))
                .build();
    }

    public RestaurantTableResponse toResponse(RestaurantTable table) {
        if (table == null) {
            return null;
        }
        return RestaurantTableResponse.builder()
                .id(table.getId())
                .restaurantId(table.getRestaurant() != null ? table.getRestaurant().getId() : null)
                .restaurantName(table.getRestaurant() != null ? table.getRestaurant().getName() : null)
                .capacity(table.getCapacity())
                .status(table.getStatus().name())
                .build();
    }
}

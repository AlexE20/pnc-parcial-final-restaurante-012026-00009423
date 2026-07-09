package com.uca.pncparcialfinalrestaurante.order.mapper;

import com.uca.pncparcialfinalrestaurante.order.domain.dto.OrderRequest;
import com.uca.pncparcialfinalrestaurante.order.domain.dto.OrderResponse;
import com.uca.pncparcialfinalrestaurante.order.domain.entity.Order;
import com.uca.pncparcialfinalrestaurante.order.domain.entity.OrderStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OrderMapper {

    public Order toEntity(OrderRequest request) {
        if (request == null) {
            return null;
        }
        return Order.builder()
                .table(request.getTable())
                .products(request.getProducts())
                .status(OrderStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public OrderResponse toResponse(Order order) {
        if (order == null) {
            return null;
        }
        return OrderResponse.builder()
                .id(order.getId())
                .clientUsername(order.getClient() != null ? order.getClient().getUsername() : null)
                .tableId(order.getTable() != null ? order.getTable().getId() : null)
                .restaurantId(order.getTable() != null && order.getTable().getRestaurant() != null ?
                        order.getTable().getRestaurant().getId() : null)
                .restaurantName(order.getTable() != null && order.getTable().getRestaurant() != null ?
                        order.getTable().getRestaurant().getName() : null)
                .status(order.getStatus().name())
                .products(order.getProducts())
                .build();
    }
}

package com.uca.pncparcialfinalrestaurante.order.service;

import com.uca.pncparcialfinalrestaurante.order.domain.dto.OrderRequest;
import com.uca.pncparcialfinalrestaurante.order.domain.dto.OrderResponse;
import com.uca.pncparcialfinalrestaurante.user.domain.entity.User;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(OrderRequest request, User client);
    OrderResponse getOrderById(Long id);
    List<OrderResponse> getAllOrders();
    List<OrderResponse> getOrdersByClientId(Long clientId);
    List<OrderResponse> getOrdersByRestaurantId(Long restaurantId);
    OrderResponse cancelOrder(Long id);
    OrderResponse confirmOrder(Long id);
}

package com.uca.pncparcialfinalrestaurante.order.service;

import com.uca.pncparcialfinalrestaurante.exceptions.ResourceNotFoundException;
import com.uca.pncparcialfinalrestaurante.order.domain.dto.OrderRequest;
import com.uca.pncparcialfinalrestaurante.order.domain.dto.OrderResponse;
import com.uca.pncparcialfinalrestaurante.order.domain.entity.Order;
import com.uca.pncparcialfinalrestaurante.order.domain.entity.OrderStatus;
import com.uca.pncparcialfinalrestaurante.order.domain.repository.OrderRepository;
import com.uca.pncparcialfinalrestaurante.order.mapper.OrderMapper;
import com.uca.pncparcialfinalrestaurante.table.domain.entity.RestaurantTable;
import com.uca.pncparcialfinalrestaurante.table.domain.repository.RestaurantTableRepository;
import com.uca.pncparcialfinalrestaurante.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final RestaurantTableRepository tableRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderResponse createOrder(OrderRequest request, User client) {
        RestaurantTable table = tableRepository.findById(request.getTable().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Table not found with id: " + request.getTable().getId()));

        // Verification: If creator is a Turn Manager (RESTAURANT), verify that the table belongs to their restaurant branch
        if (client.getRole().getName().name().equals("RESTAURANT")) {
            if (client.getRestaurant() == null ||
                table.getRestaurant() == null ||
                !table.getRestaurant().getId().equals(client.getRestaurant().getId())) {
                throw new IllegalArgumentException("You can only create orders for tables in your own branch");
            }
        }

        Order order = orderMapper.toEntity(request);
        order.setTable(table);
        order.setClient(client);
        order.setStatus(OrderStatus.PENDING);
        order.setCreatedAt(java.time.LocalDateTime.now());

        Order savedOrder = orderRepository.save(order);
        return orderMapper.toResponse(savedOrder);
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
        return orderMapper.toResponse(order);
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderResponse> getOrdersByClientId(Long clientId) {
        return orderRepository.findByClientId(clientId).stream()
                .map(orderMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderResponse> getOrdersByRestaurantId(Long restaurantId) {
        return orderRepository.findByTableRestaurantId(restaurantId).stream()
                .map(orderMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponse cancelOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
        order.setStatus(OrderStatus.CANCELLED);
        Order savedOrder = orderRepository.save(order);
        return orderMapper.toResponse(savedOrder);
    }

    @Override
    public OrderResponse confirmOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
        order.setStatus(OrderStatus.CONFIRMED);
        Order savedOrder = orderRepository.save(order);
        return orderMapper.toResponse(savedOrder);
    }
}

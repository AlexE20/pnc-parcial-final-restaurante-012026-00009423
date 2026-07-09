package com.uca.pncparcialfinalrestaurante.order.domain.repository;

import com.uca.pncparcialfinalrestaurante.order.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findOrder(Long clientId);
    List<Order> findByTableRestaurantId(Long restaurantId);
}

package com.codecool.vizsgaremek.repository;

import com.codecool.vizsgaremek.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "select o from Order o where o.orderTime<=?1")
    List<Order> findOrdersBeforeDate(LocalDateTime date);
}

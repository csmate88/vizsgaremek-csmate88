package com.codecool.vizsgaremek.repository;

import com.codecool.vizsgaremek.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}

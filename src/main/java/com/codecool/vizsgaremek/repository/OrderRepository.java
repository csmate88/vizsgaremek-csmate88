package com.codecool.vizsgaremek.repository;

import com.codecool.vizsgaremek.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

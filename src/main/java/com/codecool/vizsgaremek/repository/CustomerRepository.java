package com.codecool.vizsgaremek.repository;

import com.codecool.vizsgaremek.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}

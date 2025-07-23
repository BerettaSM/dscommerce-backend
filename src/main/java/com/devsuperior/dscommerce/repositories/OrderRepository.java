package com.devsuperior.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dscommerce.domain.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {   
}

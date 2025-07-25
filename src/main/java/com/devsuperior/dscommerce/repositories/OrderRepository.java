package com.devsuperior.dscommerce.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dscommerce.domain.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("""
        FROM Order o
        JOIN FETCH o.items
        WHERE o.client.id = :clientId
    """)
    Page<Order> findAllByClientId(Long clientId, Pageable pageable);

}

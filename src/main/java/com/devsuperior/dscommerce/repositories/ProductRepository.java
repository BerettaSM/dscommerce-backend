package com.devsuperior.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dscommerce.domain.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

package com.devsuperior.dscommerce.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dscommerce.domain.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("""
        FROM Product p
        WHERE UPPER(p.name) LIKE CONCAT('%', UPPER(:searchTerm), '%')        
    """)
    Page<Product> searchByName(String searchTerm, Pageable pageable);

}

package com.devsuperior.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dscommerce.domain.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

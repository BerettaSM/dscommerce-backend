package com.devsuperior.dscommerce.domain.dto;

import java.io.Serializable;

import com.devsuperior.dscommerce.domain.entities.Product;

public record ProductDTO(Long id, String name, String description, Double price, String imgUrl) implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public ProductDTO(Product product) {
        this(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            product.getImgUrl());
    }

    public static ProductDTO from(Product product) {
        return new ProductDTO(product);
    }

    public Product toEntity() {
        return new Product(null, name, description, price, imgUrl);
    }

}

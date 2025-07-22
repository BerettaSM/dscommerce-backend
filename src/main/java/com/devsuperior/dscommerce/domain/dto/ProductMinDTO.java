package com.devsuperior.dscommerce.domain.dto;

import java.io.Serializable;

import com.devsuperior.dscommerce.domain.entities.Product;

public record ProductMinDTO(
        Long id,
        String name,
        Double price,
        String imgUrl) implements Serializable {

    private static final long serialVersionUID = 1L;

    public ProductMinDTO(Product product) {
        this(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getImgUrl());
    }

    public static ProductMinDTO from(Product product) {
        return new ProductMinDTO(product);
    }

}

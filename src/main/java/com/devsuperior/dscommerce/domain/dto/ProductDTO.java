package com.devsuperior.dscommerce.domain.dto;

import java.io.Serializable;

import com.devsuperior.dscommerce.domain.entities.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProductDTO(
    Long id,

    @NotBlank(message = "Field required")
    @Size(min = 3, max = 80, message = "Name must have between 3 and 80 characters")
    String name,

    @NotBlank(message = "Field required")
    @Size(min = 10, message = "Description must have at least 10 characters")
    String description,

    @NotNull(message = "Field required")
    @Positive(message = "Price must be a positive value")
    Double price,
    
    String imgUrl
) implements Serializable {

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

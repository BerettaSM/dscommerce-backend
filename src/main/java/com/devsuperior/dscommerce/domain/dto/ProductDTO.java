package com.devsuperior.dscommerce.domain.dto;

import java.io.Serializable;

import com.devsuperior.dscommerce.domain.entities.Product;

import lombok.Getter;

@Getter
public class ProductDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imgUrl;

    private ProductDTO(Product product) {
        id = product.getId();
        name = product.getName();
        description = product.getDescription();
        price = product.getPrice();
        imgUrl = product.getImgUrl();
    }

    public static ProductDTO from(Product product) {
        return new ProductDTO(product);
    }

}

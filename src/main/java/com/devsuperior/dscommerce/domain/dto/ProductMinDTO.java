package com.devsuperior.dscommerce.domain.dto;

import com.devsuperior.dscommerce.domain.entities.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class ProductMinDTO {

    private Long id;
    private String name;
    private Double price;
    private String imgUrl;

    public ProductMinDTO(Product product) {
        id = product.getId();
        name = product.getName();
        price = product.getPrice();
        imgUrl = product.getImgUrl();
    }

    public static ProductMinDTO from(Product product) {
        return new ProductMinDTO(product);
    }

}

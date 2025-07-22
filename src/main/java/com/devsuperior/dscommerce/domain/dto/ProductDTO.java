package com.devsuperior.dscommerce.domain.dto;

import com.devsuperior.dscommerce.domain.entities.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class ProductDTO {

    private Long id;

    @NotBlank(message = "Field required")
    @Size(min = 3, max = 80, message = "Name must have between 3 and 80 characters")
    private String name;

    @NotBlank(message = "Field required")
    @Size(min = 10, message = "Description must have at least 10 characters")
    private String description;

    @NotNull(message = "Field required")
    @Positive(message = "Price must be a positive value")
    private Double price;
    
    private String imgUrl;
    
    public ProductDTO(Product product) {
        id = product.getId();
        name = product.getName();
        description = product.getDescription();
        price = product.getPrice();
        imgUrl = product.getImgUrl();
    }

    public static ProductDTO from(Product product) {
        return new ProductDTO(product);
    }

    public Product toEntity() {
        return new Product(null, name, description, price, imgUrl);
    }

}

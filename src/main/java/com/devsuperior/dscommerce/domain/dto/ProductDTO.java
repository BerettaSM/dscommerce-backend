package com.devsuperior.dscommerce.domain.dto;

import java.util.ArrayList;
import java.util.List;

import com.devsuperior.dscommerce.domain.entities.Category;
import com.devsuperior.dscommerce.domain.entities.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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

    @NotEmpty(message = "Must have at least 1 category")
    private final List<CategoryDTO> categories = new ArrayList<>();

    public ProductDTO(Product product) {
        id = product.getId();
        name = product.getName();
        description = product.getDescription();
        price = product.getPrice();
        imgUrl = product.getImgUrl();
        for (Category category : product.getCategories()) {
            categories.add(CategoryDTO.from(category));
        }
    }

    public static ProductDTO from(Product product) {
        return new ProductDTO(product);
    }

    public Product toEntity() {
        Product product = new Product(null, name, description, price, imgUrl);
        categories.stream()
                .map(c -> new Category(c.getId(), c.getName()))
                .forEach(product::addCategory);
        return product;
    }

}

package com.devsuperior.dscommerce.domain.dto;

import com.devsuperior.dscommerce.domain.entities.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class CategoryDTO {

    private Long id;
    private String name;
    
    public CategoryDTO(Category category) {
        id = category.getId();
        name = category.getName();
    }

    public static CategoryDTO from(Category category) {
        return new CategoryDTO(category);
    }

    public Category toEntity() {
        return new Category(null, name);
    }

}

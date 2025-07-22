package com.devsuperior.dscommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscommerce.domain.dto.CategoryDTO;
import com.devsuperior.dscommerce.domain.dto.ProductDTO;
import com.devsuperior.dscommerce.domain.dto.ProductMinDTO;
import com.devsuperior.dscommerce.domain.entities.Category;
import com.devsuperior.dscommerce.domain.entities.Product;
import com.devsuperior.dscommerce.repositories.CategoryRepository;
import com.devsuperior.dscommerce.repositories.ProductRepository;
import com.devsuperior.dscommerce.services.exceptions.DatabaseException;
import com.devsuperior.dscommerce.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class ProductService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        return productRepository.findById(id)
            .map(ProductDTO::from)
            .orElseThrow(ResourceNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public Page<ProductMinDTO> findAll(String searchTerm, Pageable pageable) {
        return productRepository.searchByName(searchTerm, pageable)
            .map(ProductMinDTO::from);
    }

    @Transactional
    public ProductDTO save(ProductDTO dto) {
        Product saved = productRepository.save(dto.toEntity());
        // Repopulate categories to return them inside response
        saved.getCategories().clear();
        categoryRepository.findAllById(
            dto.getCategories()
                .stream()
                .map(CategoryDTO::getId)
                .toList())
            .forEach(saved::addCategory);
        return ProductDTO.from(saved);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        Product product = productRepository.getReferenceById(id);
        try {
            copyDtoToEntity(dto, product);
            Product saved = productRepository.save(product);
            return ProductDTO.from(saved);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException();
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteById(Long id) {
        if(!productRepository.existsById(id)) {
            throw new ResourceNotFoundException();
        }
        try {
            productRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException();
        }
    }

    private void copyDtoToEntity(ProductDTO dto, Product product) {
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setImgUrl(dto.getImgUrl());
        product.getCategories().clear();
        dto.getCategories()
            .stream()
            .map(c -> new Category(c.getId(), c.getName()))
            .forEach(product::addCategory);
    }

}

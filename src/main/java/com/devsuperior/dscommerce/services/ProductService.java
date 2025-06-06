package com.devsuperior.dscommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscommerce.domain.dto.ProductDTO;
import com.devsuperior.dscommerce.repositories.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductService {
    
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public Optional<ProductDTO> findById(Long id) {
        return productRepository.findById(id)
            .map(ProductDTO::from);
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        return productRepository.findAll(pageable)
            .map(ProductDTO::from);
    }

    @Transactional
    public ProductDTO save(ProductDTO dto) {
        return ProductDTO.from(productRepository.save(dto.toEntity()));
    }

}

package com.devsuperior.dscommerce.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.devsuperior.dscommerce.domain.dto.ProductDTO;
import com.devsuperior.dscommerce.services.ProductService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping(path = "/products",
                produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {
    
    private final ProductService productService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        return productService.findById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(productService.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<ProductDTO> save(@RequestBody ProductDTO dto, UriComponentsBuilder uriBuilder) {
        ProductDTO saved = productService.save(dto);
        URI location = uriBuilder.path("products/{id}")
            .buildAndExpand(saved.id())
            .toUri();
        return ResponseEntity.created(location).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO dto) {
        return ResponseEntity.ok(productService.update(id, dto));
    }
    
}

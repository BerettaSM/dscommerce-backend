package com.devsuperior.dscommerce.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.devsuperior.dscommerce.domain.dto.ProductDTO;
import com.devsuperior.dscommerce.security.annotations.AdminOnly;
import com.devsuperior.dscommerce.services.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {

    private final ProductService productService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll(
            @RequestParam(name = "search", required = false, defaultValue = "") String searchTerm,
            Pageable pageable) {
        return ResponseEntity.ok(productService.findAll(searchTerm, pageable));
    }

    @AdminOnly
    @PostMapping
    public ResponseEntity<ProductDTO> save(
            @RequestBody @Valid ProductDTO dto,
            UriComponentsBuilder uriBuilder,
            HttpServletRequest request) {
        ProductDTO saved = productService.save(dto);
        URI location = uriBuilder.path("{path}/{id}")
                .buildAndExpand(request.getRequestURI(), saved.id())
                .toUri();
        return ResponseEntity.created(location).body(saved);
    }

    @AdminOnly
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody @Valid ProductDTO dto) {
        return ResponseEntity.ok(productService.update(id, dto));
    }

    @AdminOnly
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

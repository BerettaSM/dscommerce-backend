package com.devsuperior.dscommerce.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.devsuperior.dscommerce.domain.dto.OrderDTO;
import com.devsuperior.dscommerce.domain.entities.User;
import com.devsuperior.dscommerce.security.annotations.AdminOnly;
import com.devsuperior.dscommerce.security.annotations.ClientOnly;
import com.devsuperior.dscommerce.services.OrderService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderController {

    private final OrderService orderService;

    @AdminOnly
    @GetMapping(path = "/{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.findById(id));
    }

    @ClientOnly
    @PostMapping
    public ResponseEntity<OrderDTO> save(
            @RequestBody @Valid OrderDTO dto,
            UriComponentsBuilder uriBuilder,
            HttpServletRequest request,
            @AuthenticationPrincipal User user) {
        OrderDTO saved = orderService.save(dto, user);
        URI location = uriBuilder.path("{path}/{id}")
                .buildAndExpand(request.getRequestURI(), saved.getId())
                .toUri();
        return ResponseEntity.created(location).body(saved);
    }

}

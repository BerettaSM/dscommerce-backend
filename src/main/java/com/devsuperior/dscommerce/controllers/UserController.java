package com.devsuperior.dscommerce.controllers;

import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dscommerce.domain.dto.UserDTO;
import com.devsuperior.dscommerce.domain.entities.User;
import com.devsuperior.dscommerce.security.annotations.Authenticated;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Authenticated
    @GetMapping("/me")
    public ResponseEntity<UserDTO> getAuthenticatedUser(@AuthenticationPrincipal User user) {
        return Optional.ofNullable(user)
            .map(UserDTO::new)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
}

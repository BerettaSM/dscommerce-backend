package com.devsuperior.dscommerce.services.exceptions;

import org.springframework.http.HttpStatus;

import com.devsuperior.dscommerce.exceptions.ApplicationException;

public class ResourceNotFoundException extends ApplicationException {

    public ResourceNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }

    public ResourceNotFoundException() {
        this("Resource not found");
    }
    
}

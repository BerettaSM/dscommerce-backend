package com.devsuperior.dscommerce.services.exceptions;

import org.springframework.http.HttpStatus;

import com.devsuperior.dscommerce.exceptions.ApplicationException;

public class DatabaseException extends ApplicationException {

    public DatabaseException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

    public DatabaseException() {
        this("Referential integrity constraint violation");
    }

}

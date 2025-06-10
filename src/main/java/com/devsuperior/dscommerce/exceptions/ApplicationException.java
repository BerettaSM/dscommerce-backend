package com.devsuperior.dscommerce.exceptions;

import org.springframework.http.HttpStatus;

public class ApplicationException extends RuntimeException {

    private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    
    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, HttpStatus status) {
        this(message);
        httpStatus = status;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}

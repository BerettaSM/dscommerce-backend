package com.devsuperior.dscommerce.controllers.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.dscommerce.domain.dto.CustomError;
import com.devsuperior.dscommerce.domain.dto.ValidationError;
import com.devsuperior.dscommerce.exceptions.ApplicationException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<CustomError> catchAll(ApplicationException e, HttpServletRequest request) {
        return ResponseEntity.status(e.getHttpStatus().value())
                .body(CustomError.from(e, request.getServletPath()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> validationError(MethodArgumentNotValidException e, HttpServletRequest request) {
        ValidationError err = ValidationError.from(e, request.getRequestURI());
        return ResponseEntity.status(err.getStatus()).body(err);
    }

}

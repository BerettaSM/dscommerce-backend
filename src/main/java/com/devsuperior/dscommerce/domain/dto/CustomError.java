package com.devsuperior.dscommerce.domain.dto;

import java.time.Instant;

import com.devsuperior.dscommerce.exceptions.ApplicationException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomError {

    private Integer status;
    private String error;
    private String path;
    private Instant timestamp;

    public CustomError(Integer status, String error, String path) {
        this.status = status;
        this.error = error;
        this.path = path;
        timestamp = Instant.now();
    }

    public static CustomError from(ApplicationException e, String path) {
        return new CustomError(
            e.getHttpStatus().value(),
            e.getMessage(),
            path);
    }

}

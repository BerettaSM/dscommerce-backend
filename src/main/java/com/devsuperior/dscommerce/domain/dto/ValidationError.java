package com.devsuperior.dscommerce.domain.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.devsuperior.dscommerce.exceptions.ExceptionUtils;

import lombok.Getter;

@Getter
public class ValidationError extends CustomError {

    private List<ErrorEntry> errors = new ArrayList<>();

    public ValidationError(Integer status, String error, String path, Instant instant, List<ErrorEntry> errors) {
        super(status, error, path, instant);
        this.errors.addAll(errors);
    }

    public ValidationError(Integer status, String error, String path, List<ErrorEntry> errors) {
        this(status, error, path, Instant.now(), errors);
    }

    public static ValidationError from(MethodArgumentNotValidException e, String path) {
        List<ErrorEntry> errors = ExceptionUtils.gatherErrorsFromException(e);
        return new ValidationError(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Validation error(s)",
                path,
                errors);
    }

}

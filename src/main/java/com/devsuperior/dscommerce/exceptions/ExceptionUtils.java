package com.devsuperior.dscommerce.exceptions;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.devsuperior.dscommerce.domain.dto.ErrorEntry;


public abstract class ExceptionUtils {

    public static List<ErrorEntry> gatherErrorsFromException(MethodArgumentNotValidException e) {
        return e.getFieldErrors()
                .stream()
                .collect(
                    Collectors.groupingBy(
                        FieldError::getField,
                        Collectors.mapping(
                            FieldError::getDefaultMessage,
                            Collectors.toList())))
                .entrySet()
                .stream()
                .map(ErrorEntry::new)
                .toList();
    }

}

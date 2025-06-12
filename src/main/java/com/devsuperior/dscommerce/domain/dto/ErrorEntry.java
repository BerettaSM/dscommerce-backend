package com.devsuperior.dscommerce.domain.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.Getter;

@Getter
public class ErrorEntry {

    private String fieldName;
    private List<String> messages = new ArrayList<>();

    public ErrorEntry(String fieldName, List<String> messages) {
        this.fieldName = fieldName;
        this.messages = List.copyOf(messages);
    }

    public ErrorEntry(Map.Entry<String, List<String>> entry) {
        this(entry.getKey(), entry.getValue());
    }

}

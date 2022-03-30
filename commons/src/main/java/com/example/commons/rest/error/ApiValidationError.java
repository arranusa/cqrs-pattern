package com.example.commons.rest.error;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ApiValidationError extends ApiSubError {
    private String message;

    ApiValidationError(String object, String message) {
        this.message = message;
    }

    public ApiValidationError(String object, String field, Object rejectedValue, String message) {
        this.message = field + " " +message;
    }
}

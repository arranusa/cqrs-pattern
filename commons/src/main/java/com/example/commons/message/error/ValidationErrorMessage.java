package com.example.commons.message.error;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class ValidationErrorMessage extends SubErrorMessage {
    private String object;
    private String field;
    private String message;

    ValidationErrorMessage(String object, String message) {
        this.object = object;
        this.message = message;
    }
}

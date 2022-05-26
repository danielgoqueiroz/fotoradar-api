package com.danielqueiroz.fotoradar.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ValidationException extends Exception {
    private String message;
}

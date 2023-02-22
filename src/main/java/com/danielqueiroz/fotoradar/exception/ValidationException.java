package com.danielqueiroz.fotoradar.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ValidationException extends Exception {
    private String message;
}

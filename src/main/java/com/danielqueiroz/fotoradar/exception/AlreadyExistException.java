package com.danielqueiroz.fotoradar.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AlreadyExistException extends Exception {
    private String message;

}

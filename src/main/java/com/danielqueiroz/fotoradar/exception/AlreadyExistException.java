package com.danielqueiroz.fotoradar.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AlreadyExistException extends Exception {

    private String message;

}

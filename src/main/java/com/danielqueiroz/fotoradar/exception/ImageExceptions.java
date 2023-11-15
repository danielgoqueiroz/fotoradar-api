package com.danielqueiroz.fotoradar.exception;

public class ImageExceptions extends RuntimeException{

    public ImageExceptions(String message, Exception e) {
        super(message, e);
    }

}

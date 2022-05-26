package com.danielqueiroz.fotoradar.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class NoticeException extends Exception{
    private String Message;
}

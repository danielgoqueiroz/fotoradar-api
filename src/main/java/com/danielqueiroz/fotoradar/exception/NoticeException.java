package com.danielqueiroz.fotoradar.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NoticeException extends Exception{
    private String Message;
}

package com.rafaelpavan.exceptions.user;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class InvalidDocumentException extends RuntimeException{
    public InvalidDocumentException() {super("CPF inválido.");}

    public InvalidDocumentException(String message) {super(message);}
}

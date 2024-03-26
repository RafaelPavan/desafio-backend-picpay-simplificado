package com.rafaelpavan.exceptions.user;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class InvalidEmailException extends RuntimeException{
    public InvalidEmailException() {super("E-mail inválido.");}

    public InvalidEmailException(String message) {super(message);}
}

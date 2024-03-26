package com.rafaelpavan.exceptions.user;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class DuplicateEmailException extends RuntimeException{
    public DuplicateEmailException() {super("E-mail já cadastrado.");}

    public DuplicateEmailException(String message) {super(message);}
}

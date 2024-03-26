package com.rafaelpavan.exceptions.user;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class DuplicateDcoException extends RuntimeException{
    public DuplicateDcoException() {super("CPF já cadastrado.");}

    public DuplicateDcoException(String message) {super(message);}

}

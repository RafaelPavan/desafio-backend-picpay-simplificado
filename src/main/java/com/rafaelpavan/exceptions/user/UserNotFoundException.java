package com.rafaelpavan.exceptions.user;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {super("Usuário não encontrado.");}

    public UserNotFoundException(String message) {super(message);}
}

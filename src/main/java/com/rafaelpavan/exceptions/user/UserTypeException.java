package com.rafaelpavan.exceptions.user;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class UserTypeException extends RuntimeException{

    public UserTypeException() {super("Tipo de usuário inválido. Escolha entre COMMON ou MERCHANT.");}

    public UserTypeException(String message) {super(message);}
}

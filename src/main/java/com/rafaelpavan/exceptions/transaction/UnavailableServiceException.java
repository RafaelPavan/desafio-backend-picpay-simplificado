package com.rafaelpavan.exceptions.transaction;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class UnavailableServiceException extends RuntimeException{
    public UnavailableServiceException() {super("Serviço indisponível no momento.");}

    public UnavailableServiceException(String message) {super(message);}
}

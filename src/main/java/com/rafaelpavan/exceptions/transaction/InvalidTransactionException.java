package com.rafaelpavan.exceptions.transaction;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class InvalidTransactionException extends  RuntimeException{
    public InvalidTransactionException() {super("Transação Inválida.");}

    public InvalidTransactionException(String message) {super(message);}
}

package com.rafaelpavan.exceptions;

import com.rafaelpavan.exceptions.transaction.InvalidTransactionException;
import com.rafaelpavan.exceptions.transaction.UnavailableServiceException;
import com.rafaelpavan.exceptions.user.*;
import com.rafaelpavan.models.dtos.validations.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(DuplicateDcoException.class)
    public ResponseEntity<Object> handleDuplicateDoc(DuplicateDcoException e){
        return ResponseEntity.internalServerError().body(e.getMessage());
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<Object> handleDuplicateEmail(DuplicateEmailException e){
        return ResponseEntity.internalServerError().body(e.getMessage());
    }

    @ExceptionHandler(InvalidDocumentException.class)
    public ResponseEntity<Object> handleInvalidDoc(InvalidDocumentException e){
        return ResponseEntity.internalServerError().body(e.getMessage());
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<Object> handleInvalidEmail(InvalidEmailException e){
        return ResponseEntity.internalServerError().body(e.getMessage());
    }

    @ExceptionHandler(BlankOrNullFieldsException.class)
    public ResponseEntity<Object> threat404(BlankOrNullFieldsException e){
        List<ErrorDto> errors = e.getErrors();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFound(UserNotFoundException e){
        return ResponseEntity.internalServerError().body(e.getMessage());
    }

    @ExceptionHandler(UnavailableServiceException.class)
    public ResponseEntity<Object> handleServiceUnavailable(UnavailableServiceException e){
        return ResponseEntity.internalServerError().body(e.getMessage());
    }
    @ExceptionHandler(InvalidTransactionException.class)
    public ResponseEntity<Object> handleInvalidTransaction(InvalidTransactionException e){
        return ResponseEntity.internalServerError().body(e.getMessage());
    }

}

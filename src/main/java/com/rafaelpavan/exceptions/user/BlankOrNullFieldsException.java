package com.rafaelpavan.exceptions.user;
import com.rafaelpavan.models.dtos.validations.ErrorDto;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.List;

@ControllerAdvice
public class BlankOrNullFieldsException extends RuntimeException {

    private List<ErrorDto> errors;

    public BlankOrNullFieldsException(List<ErrorDto> errors) {
        super("Campos obrigatórios não preenchidos.");
        this.errors = errors;
    }

    public List<ErrorDto> getErrors() {
        return errors;
    }
}


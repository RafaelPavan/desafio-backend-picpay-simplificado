package com.rafaelpavan.validations;

import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.CPFValidator;
import com.rafaelpavan.exceptions.user.InvalidDocumentException;

import java.util.List;

public class CpfValidation {
    public static void validate(String cpf) {
        CPFValidator cpfValidator = new CPFValidator();
        List<ValidationMessage> errors = cpfValidator.invalidMessagesFor(cpf);
        if (!errors.isEmpty())
            throw new InvalidDocumentException();
    }
}

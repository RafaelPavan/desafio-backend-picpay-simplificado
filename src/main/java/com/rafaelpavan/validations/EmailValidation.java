package com.rafaelpavan.validations;

import com.rafaelpavan.exceptions.user.InvalidEmailException;
import org.apache.commons.validator.routines.EmailValidator;

public class EmailValidation {
    public static void emailValidation(String email) {

        var isEmailValid = EmailValidator.getInstance().isValid(email);
        if (!isEmailValid)
            throw new InvalidEmailException();

    }
}

package com.rafaelpavan.validations.user;

import com.rafaelpavan.models.dtos.user.UserDto;
import com.rafaelpavan.models.dtos.validations.ErrorDto;
import com.rafaelpavan.validations.CpfValidation;
import com.rafaelpavan.validations.EmailValidation;

import java.util.ArrayList;
import java.util.List;

public class SaveUserValidation {

    public static List<ErrorDto> execute(UserDto userDto){

        List<ErrorDto> errors = new ArrayList<>();

        if(userDto.firstName().isBlank()){
            errors.add(new ErrorDto("firstName", "Campo Obrigatório"));
        }

        if(userDto.lastName().isBlank()){
            errors.add(new ErrorDto("lastName", "Campo Obrigatório"));
        }

        if(userDto.document().isEmpty()){
            errors.add(new ErrorDto("document", "Campo Obrigatório"));
        }else CpfValidation.validate(userDto.document());

        if(userDto.password().isBlank()){
            errors.add(new ErrorDto("password", "Campo Obrigatório"));
        }

        if(userDto.email().isEmpty()){
            errors.add(new ErrorDto("e-mail", "Campo Obrigatório"));
        }else EmailValidation.emailValidation(userDto.email());


        if(userDto.userType().isBlank() ){
            errors.add(new ErrorDto("userType", "Campo Obrigatório"));
        }

        return errors;
    }
}

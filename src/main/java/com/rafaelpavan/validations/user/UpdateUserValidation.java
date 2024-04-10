package com.rafaelpavan.validations.user;

import com.rafaelpavan.models.dtos.user.UpdateUserDto;
import com.rafaelpavan.models.dtos.validations.ErrorDto;
import com.rafaelpavan.validations.EmailValidation;

import java.util.ArrayList;
import java.util.List;

public class UpdateUserValidation {
    public static List<ErrorDto> execute(UpdateUserDto updateUserDto){

        List<ErrorDto> errors = new ArrayList<>();

        if(updateUserDto.firstName().isBlank()){
            errors.add(new ErrorDto("firstName", "Campo Obrigatório"));
        }

        if(updateUserDto.lastName().isBlank()){
            errors.add(new ErrorDto("lastName", "Campo Obrigatório"));
        }

        if(updateUserDto.email().isBlank()){
            errors.add(new ErrorDto("e-mail", "Campo Obrigatório"));
        }else EmailValidation.emailValidation(updateUserDto.email());


        if(updateUserDto.userType() == null){
            errors.add(new ErrorDto("userType", "Campo Obrigatório"));
        }

        return errors;
    }
}

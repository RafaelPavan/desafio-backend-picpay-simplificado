package com.rafaelpavan.models.dtos.user;

import com.rafaelpavan.models.enums.user.UserType;
import com.rafaelpavan.validations.enums.ValueOfEnum;

import java.math.BigDecimal;

public record UserDto(
        String firstName, String lastName, String document,String email,
         String password, BigDecimal balance, @ValueOfEnum(enumClass = UserType.class) String userType) {
}
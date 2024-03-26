package com.rafaelpavan.models.dtos.user;

import com.rafaelpavan.models.enums.user.UserType;

import java.math.BigDecimal;

public record UserDto(
        String firstName, String lastName, String document,
        BigDecimal balance, String password,String email, UserType userType) {
}
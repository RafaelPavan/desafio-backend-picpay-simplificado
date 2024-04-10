package com.rafaelpavan.models.dtos.user;

import com.rafaelpavan.models.enums.user.UserType;

public record UpdateUserDto(
        String firstName, String lastName, String email, UserType userType)  {
}

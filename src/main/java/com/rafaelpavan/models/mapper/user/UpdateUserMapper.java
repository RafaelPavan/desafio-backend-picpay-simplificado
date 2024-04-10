package com.rafaelpavan.models.mapper.user;

import com.rafaelpavan.models.dtos.user.UpdateUserDto;
import com.rafaelpavan.models.entities.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UpdateUserMapper {

    User toEntity (UpdateUserDto updateUserDto);

}

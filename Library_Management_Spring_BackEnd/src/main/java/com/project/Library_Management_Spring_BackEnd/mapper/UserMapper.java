package com.project.Library_Management_Spring_BackEnd.mapper;

import com.project.Library_Management_Spring_BackEnd.dto.request.UserCreationDto;
import com.project.Library_Management_Spring_BackEnd.dto.request.UserDto;
import com.project.Library_Management_Spring_BackEnd.dto.request.UserUpdatingDto;
import com.project.Library_Management_Spring_BackEnd.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User UserCreationDtoToUser(UserCreationDto userCreationDto);

    @Mapping(target = "roles", ignore = true)
    User updateUser(@MappingTarget User user, UserUpdatingDto userUpdatingDto);

    UserDto UserToUserDto(User user);
}

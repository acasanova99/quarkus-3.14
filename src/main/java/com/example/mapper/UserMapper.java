package com.example.mapper;

import com.example.dto.UserDto;
import com.example.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "jakarta")
public interface UserMapper {

    User toEntity(UserDto dto);

    @Mapping(target = "id", ignore = true)
    UserDto fromEntity(User entity);
}

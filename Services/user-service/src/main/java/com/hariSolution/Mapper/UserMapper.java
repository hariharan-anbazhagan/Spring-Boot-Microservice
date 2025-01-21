package com.hariSolution.Mapper;

import com.hariSolution.model.User;
import com.hariSolution.model.UserDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")  // Spring will manage the bean
public interface UserMapper {


    User toEntity(UserDto userDto);

    @InheritInverseConfiguration
    UserDto toDto(User user);

}

package com.bell.bellschooll.mapper;

import com.bell.bellschooll.dto.request.UserInSaveDto;
import com.bell.bellschooll.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
   // @Mapping(target = "coutry",ignore = true)
    User dtoToDomain(UserInSaveDto userInSaveDto);
}

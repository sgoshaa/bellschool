package com.bell.bellschooll.mapper;

import com.bell.bellschooll.dto.request.UpdateUserInDto;
import com.bell.bellschooll.dto.request.UserInSaveDto;
import com.bell.bellschooll.dto.response.UserOutDto;
import com.bell.bellschooll.dto.response.UserOutListDto;
import com.bell.bellschooll.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring",uses = DocumentMapper.class)
public interface UserMapper {

    User dtoToDomain(UserInSaveDto userInSaveDto);

    @Mapping(target = "docName",expression = "java(user.getDocument().getDocType().getName())")
    @Mapping(target = "docNumber",expression = "java(user.getDocument().getDocNumber())")
    @Mapping(target = "docDate",expression = "java(user.getDocument().getDocDate())")
    @Mapping(target = "citizenshipName",expression = "java(user.getCountry().getName())")
    @Mapping(target = "citizenshipCode",expression = "java(user.getCountry().getCode())")
    UserOutDto domainToDto(User user);

    UserOutListDto domainToOutListDto(User user);

    User toDomainUpdate(UpdateUserInDto updateUserInDto);

    User updateUserFromDto(UpdateUserInDto updateUserInDto,@MappingTarget User user);
}

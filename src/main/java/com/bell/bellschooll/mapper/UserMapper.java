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

    @Mapping(target = "docName",source = "document.docType.name")
    @Mapping(target = "docNumber",source = "document.docNumber")
    @Mapping(target = "docDate",source = "document.docDate")
    @Mapping(target = "citizenshipName",source = "country.name")
    @Mapping(target = "citizenshipCode",source = "country.code")
    UserOutDto domainToDto(User user);

    UserOutListDto domainToOutListDto(User user);

    User toDomainUpdate(UpdateUserInDto updateUserInDto);

    User updateUserFromDto(UpdateUserInDto updateUserInDto,@MappingTarget User user);
}

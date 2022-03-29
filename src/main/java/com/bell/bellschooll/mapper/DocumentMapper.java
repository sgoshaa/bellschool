package com.bell.bellschooll.mapper;

import com.bell.bellschooll.dto.request.UpdateUserInDto;
import com.bell.bellschooll.dto.request.UserInSaveDto;
import com.bell.bellschooll.model.Document;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface DocumentMapper {

    Document dtoToDomain(UserInSaveDto userInSaveDto);

    @Mapping(target = "id",ignore = true)
    Document dtoToDomainUpdate(UpdateUserInDto updateUserInDto);

    @Mapping(target = "id",ignore = true)
    Document updateDocument(UpdateUserInDto updateUserInDto,@MappingTarget Document document);
}

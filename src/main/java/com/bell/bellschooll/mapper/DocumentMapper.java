package com.bell.bellschooll.mapper;

import com.bell.bellschooll.dto.request.UserInSaveDto;
import com.bell.bellschooll.model.Document;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DocumentMapper {

    Document dtoToDomain(UserInSaveDto userInSaveDto);
}

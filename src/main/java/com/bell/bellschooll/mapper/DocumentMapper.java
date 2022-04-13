package com.bell.bellschooll.mapper;

import com.bell.bellschooll.dto.request.UpdateUserInDto;
import com.bell.bellschooll.dto.request.UserInSaveDto;
import com.bell.bellschooll.dto.response.DocumentDto;
import com.bell.bellschooll.model.Document;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * Маппер для работы с Document
 */
@Mapper(componentModel = "spring")
public interface DocumentMapper {
    /**
     * Метод для маппинга из RequestDTO  UserInSaveDto в Document
     *
     * @param userInSaveDto
     * @return
     */
    Document dtoToDomain(UserInSaveDto userInSaveDto);

    /**
     * Метод для маппинга из UpdateUserInDto в Document
     *
     * @param updateUserInDto
     * @return
     */
    @Mapping(target = "id", ignore = true)
    Document dtoToDomainUpdate(UpdateUserInDto updateUserInDto);

    /**
     * Метод для маппинга из UpdateUserInDto в Document
     *
     * @param updateUserInDto
     * @param document
     * @return
     */
    @Mapping(target = "id", ignore = true)
    Document updateDocument(UpdateUserInDto updateUserInDto, @MappingTarget Document document);

    @Mapping(target = "name",source = "docType.name")
    @Mapping(target = "code",source = "docType.code")
    DocumentDto toDto(Document document);

    List<DocumentDto> toListDto(List<Document> documentList);
}

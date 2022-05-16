package com.bell.bellschooll.mapper;

import com.bell.bellschooll.dto.request.UpdateUserInDto;
import com.bell.bellschooll.dto.request.UserInSaveDto;
import com.bell.bellschooll.dto.response.DocumentDto;
import com.bell.bellschooll.model.Document;
import com.bell.bellschooll.model.DocumentType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Маппер для работы с Document
 */
@Mapper(componentModel = "spring")
public interface DocumentMapper {
    /**
     * Метод для маппинга из RequestDTO  UserInSaveDto в Document
     *
     * @param userInSaveDto Объект с полями для отображения в документе
     * @return Document
     */
    @Mapping(target = "number", source = "docNumber")
    @Mapping(target = "date", source = "docDate")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "docType", ignore = true)
    Document dtoToDomain(UserInSaveDto userInSaveDto);

    /**
     * Метод для маппинга из UpdateUserInDto в Document
     *
     * @param updateUserInDto Объект с полями для отображения в документе
     * @return Document
     */
    @Mapping(target = "id", source = "currentDocument.id")
    @Mapping(target = "number", source = "updateUserInDto.docNumber")
    @Mapping(target = "date", source = "updateUserInDto.docDate")
    Document dtoToDomain(UpdateUserInDto updateUserInDto, Document currentDocument);

    /**
     * Метод для маппинга DocumentType в Dto
     *
     * @param document объект с полями для отображения
     * @return DocumentDto
     */
    @Mapping(target = "name", source = "name")
    @Mapping(target = "code", source = "code")
    DocumentDto toDto(DocumentType document);

    /**
     * Метод для маппинга из списка документов в список Dto документа
     *
     * @param documentList Список документов
     * @return List<DocumentDto>
     */
    List<DocumentDto> toListDto(List<DocumentType> documentList);
}

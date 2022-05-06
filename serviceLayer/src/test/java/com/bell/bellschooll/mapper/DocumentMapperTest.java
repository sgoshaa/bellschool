package com.bell.bellschooll.mapper;

import com.bell.bellschooll.dto.request.UpdateUserInDto;
import com.bell.bellschooll.dto.request.UserInSaveDto;
import com.bell.bellschooll.dto.response.DocumentDto;
import com.bell.bellschooll.model.Document;
import com.bell.bellschooll.model.DocumentType;
import com.bell.bellschooll.util.ConstantValue;
import com.bell.bellschooll.util.UserHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {DocumentMapper.class})
@ContextConfiguration(classes = MapperTestConfig.class)
class DocumentMapperTest {

    @Autowired
    DocumentMapper documentMapper;

    @Test
    void dtoToDomain() {
        //Given
        UserInSaveDto userInSaveDto = UserHelper.createUserInSaveDto();
        Document document = new Document();
        document.setId(ConstantValue.ID);
        document.setDate(userInSaveDto.getDocDate());
        document.setNumber(userInSaveDto.getDocNumber());

        //When
        Document documentActual = documentMapper.dtoToDomain(userInSaveDto);

        //Then
        assertEquals(document.getNumber(), documentActual.getNumber());
        assertEquals(document.getDate(), documentActual.getDate());
    }

    @Test
    void dtoToDomainUpdate() {
        //Given
        UpdateUserInDto updateUserInDto = UserHelper.createUpdateUserInDto();
        Document document = new Document();
        document.setNumber(updateUserInDto.getDocNumber());
        document.setDate(updateUserInDto.getDocDate());

        //When
        Document documentActual = documentMapper.dtoToDomain(updateUserInDto, document);

        //Then
        assertEquals(document.getNumber(), documentActual.getNumber());
        assertEquals(document.getDate(), documentActual.getDate());
    }

    @Test
    void toDto() {
        //Given
        DocumentType documentTypeForTest = UserHelper.createDocumentTypeForTest();
        DocumentDto documentDto = new DocumentDto();
        documentDto.setCode(documentTypeForTest.getCode());
        documentDto.setName(documentTypeForTest.getName());

        //When
        DocumentDto documentDtoActual = documentMapper.toDto(documentTypeForTest);

        //Then
        assertEquals(documentDto.getCode(), documentDtoActual.getCode());
        assertEquals(documentDto.getName(), documentDtoActual.getName());
    }

    @Test
    void toListDto() {
        //Given
        DocumentType documentTypeForTest = UserHelper.createDocumentTypeForTest();
        ArrayList<DocumentType> documentTypeArrayList = new ArrayList<>();
        documentTypeArrayList.add(documentTypeForTest);

        //When
        List<DocumentDto> documentDtoList = documentMapper.toListDto(documentTypeArrayList);

        //Then
        assertEquals(documentTypeArrayList.size(), documentDtoList.size());
        assertEquals(documentTypeArrayList.get(0).getName(), documentDtoList.get(0).getName());
        assertEquals(documentTypeArrayList.get(0).getCode(), documentDtoList.get(0).getCode());
    }
}
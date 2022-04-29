package com.bell.bellschooll.service;

import com.bell.bellschooll.dto.response.DocumentDto;
import com.bell.bellschooll.model.DocumentType;
import com.bell.bellschooll.repository.DocumentTypeRepository;
import com.bell.bellschooll.util.DocumentHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class DocumentServiceImplTest {

    @MockBean
    DocumentTypeRepository documentTypeRepository;

    @Autowired
    DocumentService documentService;

    @Test
    void getAllDocument() {
        //Given
        DocumentType documentType = DocumentHelper.createDocumentType();
        when(documentTypeRepository.findAll()).thenReturn(List.of(documentType));

        //When
        List<DocumentDto> allDocument = documentService.getAllDocument();

        //Then
        verify(documentTypeRepository).findAll();
        assertNotNull(allDocument);
        assertThat(
                allDocument.stream()
                        .map(DocumentDto::getCode)
                        .collect(Collectors.toList()
                        ), hasItem("21")
        );
    }
}
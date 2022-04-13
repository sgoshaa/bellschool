package com.bell.bellschooll.service;

import com.bell.bellschooll.dto.response.DocumentDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class DocumentServiceTest {
    @Autowired
    DocumentService documentService;

    @Test
    void getAllDocument() {
        List<DocumentDto> allDocument = documentService.getAllDocument();
        assertNotNull(allDocument);
        assertThat(
                allDocument.stream()
                        .map(DocumentDto::getCode)
                        .collect(Collectors.toList()
                        ), hasItem("21")
        );
    }
}
package com.bell.bellschooll.repository;

import com.bell.bellschooll.model.DocumentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
class DocumentTypeRepositoryTest {

    @Autowired
    DocumentTypeRepository documentTypeRepository;

    @Test
    void getDocumentTypeByCode() {
        //Given
        String code = "21";

        //When
        Optional<DocumentType> documentTypeByCode = documentTypeRepository.getDocumentTypeByCode(code);

        //Then
        assertTrue(documentTypeByCode.isPresent());
        assertEquals(code, documentTypeByCode.get().getCode());
    }

    @Test
    void findAllDocuments() {
        //When
        List<DocumentType> all = documentTypeRepository.findAll();

        //Then
        assertTrue(all.size() > 0);
        assertThat(all.stream()
                        .map(DocumentType::getCode)
                        .collect(Collectors.toList())
                , hasItem("21"));
    }

}
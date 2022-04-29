package com.bell.bellschooll.controller;

import com.bell.bellschooll.dto.response.DocumentDto;
import com.bell.bellschooll.dto.response.ResponseDto;
import com.bell.bellschooll.service.DocumentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(DocumentController.class)
class DocumentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DocumentService documentService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void getAllDocument() throws Exception {
        //Given
        DocumentDto firstDocument = new DocumentDto();
        firstDocument.setName("first document");
        firstDocument.setCode("7777 7777");

        DocumentDto secondDocument = new DocumentDto();
        secondDocument.setName("second document");
        secondDocument.setCode("888 88888");

        List<DocumentDto> dtoList = List.of(firstDocument, secondDocument);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setData(dtoList);

        //When
        when(documentService.getAllDocument()).thenReturn(dtoList);

        //Then
        mockMvc.perform(
                        get("/api/docs"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(responseDto)));
    }
}
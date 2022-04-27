package com.bell.bellschooll.controller;

import com.bell.bellschooll.dto.response.DocumentDto;
import com.bell.bellschooll.service.DocumentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/docs")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping
    public List<DocumentDto> getAllDocument(){
        return documentService.getAllDocument();
    }
}

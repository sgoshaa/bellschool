package com.bell.bellschooll.service;

import com.bell.bellschooll.repository.DocumentRepository;
import com.bell.bellschooll.dto.response.DocumentDto;
import com.bell.bellschooll.mapper.DocumentMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;
    private final DocumentMapper documentMapper;

    public DocumentServiceImpl(DocumentRepository documentRepository, DocumentMapper documentMapper) {
        this.documentRepository = documentRepository;
        this.documentMapper = documentMapper;
    }

    /**
     * @see DocumentService#getAllDocument()
     */
    public List<DocumentDto> getAllDocument() {
        return  documentMapper.toListDto(documentRepository.findAll());
    }
}

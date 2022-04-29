package com.bell.bellschooll.service;

import com.bell.bellschooll.mapper.DocumentMapper;
import com.bell.bellschooll.dto.response.DocumentDto;
import com.bell.bellschooll.repository.DocumentTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentTypeRepository documentTypeRepository;
    private final DocumentMapper documentMapper;

    public DocumentServiceImpl(DocumentTypeRepository documentTypeRepository, DocumentMapper documentMapper) {
        this.documentTypeRepository = documentTypeRepository;
        this.documentMapper = documentMapper;
    }

    /**
     * @see DocumentService#getAllDocument()
     */
    public List<DocumentDto> getAllDocument() {
        return documentMapper.toListDto(documentTypeRepository.findAll());
    }
}

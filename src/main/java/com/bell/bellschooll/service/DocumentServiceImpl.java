package com.bell.bellschooll.service;

import com.bell.bellschooll.dao.DocumentDao;
import com.bell.bellschooll.dto.response.DocumentDto;
import com.bell.bellschooll.mapper.DocumentMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {
    private final DocumentDao documentDao;
    private final DocumentMapper documentMapper;

    public DocumentServiceImpl(DocumentDao documentDao, DocumentMapper documentMapper) {
        this.documentDao = documentDao;
        this.documentMapper = documentMapper;
    }

    public List<DocumentDto> getAllDocument() {
        return  documentMapper.toListDto(documentDao.findAll());
    }
}

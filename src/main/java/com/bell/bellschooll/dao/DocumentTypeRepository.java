package com.bell.bellschooll.dao;

import com.bell.bellschooll.model.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentType,Integer> {
    DocumentType getDocumentTypeByCode(String code);
}

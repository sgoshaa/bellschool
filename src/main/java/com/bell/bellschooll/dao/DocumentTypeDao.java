package com.bell.bellschooll.dao;

import com.bell.bellschooll.model.DocumentType;
import com.sun.xml.txw2.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentTypeDao extends JpaRepository<DocumentType,Integer> {
    DocumentType getDocumentTypeByCode(String code);
}

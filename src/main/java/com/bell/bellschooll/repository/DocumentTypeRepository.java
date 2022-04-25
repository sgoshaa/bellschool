package com.bell.bellschooll.repository;

import com.bell.bellschooll.model.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DAO для работы с DocumentType
 */
@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentType, Integer> {
    /**
     * Метод для получения типа документа по полю Код
     *
     * @param code Код типа документа
     * @return DocumentType объект,содержащий тип документа
     */
    DocumentType getDocumentTypeByCode(String code);
}

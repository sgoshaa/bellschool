package com.bell.bellschooll.repository;

import com.bell.bellschooll.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DAO для работы с Document
 */
@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {
}

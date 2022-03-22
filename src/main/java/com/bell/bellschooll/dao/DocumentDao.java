package com.bell.bellschooll.dao;

import com.bell.bellschooll.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DocumentDao extends JpaRepository<Document,Integer> {
}

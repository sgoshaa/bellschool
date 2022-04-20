package com.bell.bellschooll.repository;

import com.bell.bellschooll.model.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OfficeRepository extends JpaRepository<Office, Integer>, JpaSpecificationExecutor<Office> {
}

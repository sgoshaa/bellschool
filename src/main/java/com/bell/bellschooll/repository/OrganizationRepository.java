package com.bell.bellschooll.repository;

import com.bell.bellschooll.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface OrganizationRepository extends JpaRepository<Organization,Integer>, JpaSpecificationExecutor<Organization> {

    Optional<Organization> findById(Integer id);
}

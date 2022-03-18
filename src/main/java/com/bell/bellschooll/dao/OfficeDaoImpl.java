package com.bell.bellschooll.dao;


import com.bell.bellschooll.model.Office;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class OfficeDaoImpl  implements OfficeDao{

    private final EntityManager entityManager;

    public OfficeDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Office getOfficeById(Integer id) {
        return entityManager.find(Office.class,id);
    }

    @Override
    public void addOffice(Office office) {
        entityManager.persist(office);
    }
}

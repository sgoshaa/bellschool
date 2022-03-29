package com.bell.bellschooll.dao;


import com.bell.bellschooll.dto.request.OfficeInListDto;
import com.bell.bellschooll.model.Office;
import com.bell.bellschooll.model.Organization;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OfficeDaoImpl implements OfficeDao {

    private final EntityManager entityManager;

    public OfficeDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Office getOfficeById(Integer id) {
        return entityManager.find(Office.class, id);
    }

    @Override
    public void addOffice(Office office) {
        entityManager.persist(office);
    }

    @Override
    public List<Office> getListOffice(OfficeInListDto office, Organization organization) {
        CriteriaQuery<Office> officeCriteriaQuery = buildCriteriaOffice(office, organization);
        TypedQuery<Office> query = entityManager.createQuery(officeCriteriaQuery);
        return query.getResultList();
    }

    @Override
    public void updateOffice(Office office) {
        entityManager.merge(office);
    }

    private CriteriaQuery<Office> buildCriteriaOffice(OfficeInListDto office, Organization organization) {
        String name = office.getName();
        String phone = office.getPhone();
        Boolean isActive = office.getIsActive();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Office> officeCriteriaQuery = criteriaBuilder.createQuery(Office.class);
        Root<Office> root = officeCriteriaQuery.from(Office.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(criteriaBuilder.equal(root.get("organization"), organization));
        if (name != null && !name.isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("name"), name));
        }
        if (phone != null && !phone.isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("phone"), phone));
        }
        if (isActive != null) {
            predicates.add(criteriaBuilder.equal(root.get("isActive"), isActive));
        }
        return officeCriteriaQuery.where(predicates.toArray(new Predicate[0]));
    }

}

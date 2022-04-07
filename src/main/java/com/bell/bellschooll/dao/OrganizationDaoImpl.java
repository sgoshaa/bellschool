package com.bell.bellschooll.dao;

import com.bell.bellschooll.dto.request.OrganisationDtoRequest;
import com.bell.bellschooll.exception.ErrorException;
import com.bell.bellschooll.model.Organization;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Репозиторий для работы с Organization, реализация OrganizationDao
 */
@Repository
public class OrganizationDaoImpl implements OrganizationDao {

    private final EntityManager entityManager;

    public OrganizationDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * @see OrganizationDao#getOrganizationById(Integer)
     */
    @Override
    public Organization getOrganizationById(Integer id) {
        return entityManager.find(Organization.class, id);
    }

    /**
     * @see OrganizationDao#getListOrganizationByName(OrganisationDtoRequest)
     */
    @Override
    public List<Organization> getListOrganizationByName(OrganisationDtoRequest organisationDtoRequest) {
        CriteriaQuery<Organization> organizationCriteriaQuery = buildCriteria(organisationDtoRequest);
        TypedQuery<Organization> query = entityManager.createQuery(organizationCriteriaQuery);
        return query.getResultList();
    }

    /**
     * @see OrganizationDao#save(Organization)
     */
    @Override
    public void save(Organization organization) {
        entityManager.persist(organization);
    }

    /**
     * @see OrganizationDao#update(Organization)
     */
    @Override
    public void update(Organization organization) {
        entityManager.merge(organization);
    }

    private CriteriaQuery<Organization> buildCriteria(OrganisationDtoRequest organisationDtoRequest) {
        String name = organisationDtoRequest.getName();
        Integer inn = organisationDtoRequest.getInn();
        Boolean isActive = organisationDtoRequest.getIsActive();
        if (name.isEmpty()) {
            throw new ErrorException("Не заполнено название организации");
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Organization> organizationCriteriaQuery = criteriaBuilder.createQuery(Organization.class);
        Root<Organization> root = organizationCriteriaQuery.from(Organization.class);

        if (inn != null && isActive != null) {
            return organizationCriteriaQuery.where(
                    criteriaBuilder.equal(root.get("name"), name),
                    criteriaBuilder.equal(root.get("inn"), inn),
                    criteriaBuilder.equal(root.get("isActive"), isActive));
        } else if (isActive != null) {
            return organizationCriteriaQuery.where(
                    criteriaBuilder.equal(root.get("name"), name),
                    criteriaBuilder.equal(root.get("isActive"), isActive));
        } else if (inn != null) {
            return organizationCriteriaQuery.where(
                    criteriaBuilder.equal(root.get("name"), name),
                    criteriaBuilder.equal(root.get("inn"), inn));
        }
        return organizationCriteriaQuery.where(criteriaBuilder.equal(root.get("name"), name));
    }
}

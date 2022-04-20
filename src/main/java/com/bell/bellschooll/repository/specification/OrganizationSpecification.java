package com.bell.bellschooll.repository.specification;

import com.bell.bellschooll.dto.request.OrganisationDtoRequest;
import com.bell.bellschooll.model.Organization;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для создания спецификации для работы с Организациями
 */
@Component
public class OrganizationSpecification {
    /**
     *  метод возвращающий спецификацию по параметром запроса OrganisationDtoRequest
     * @param organisationDtoRequest параметры фильтрации организаций
     * @return Specification<Organization>
     */
    public  Specification<Organization> getSpecification(OrganisationDtoRequest organisationDtoRequest) {
        return new Specification<Organization>() {
            @Override
            public Predicate toPredicate(Root<Organization> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                predicateList.add(criteriaBuilder.equal(root.get("name"), organisationDtoRequest.getName()));

                if (organisationDtoRequest.getIsActive() != null) {
                    predicateList.add(criteriaBuilder.equal(root.get("isActive"), organisationDtoRequest.getIsActive()));
                }
                if (organisationDtoRequest.getInn() != null) {
                    predicateList.add(criteriaBuilder.equal(root.get("inn"), organisationDtoRequest.getInn()));
                }

                return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
            }
        };
    }
}

package com.bell.bellschooll.specification;


import com.bell.bellschooll.dto.request.OfficeInListDto;
import com.bell.bellschooll.model.Office;
import com.bell.bellschooll.model.Organization;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;

/**
 * Класс для создания спецификации для работы с Office
 */
@Component
public class OfficeSpecification {
    /**
     * метод для фильтрации офисов по параметрами
     * @param officeInListDto объект,содержащий параметры
     * @param organization организация
     * @return Specification<Office>
     */
    public Specification<Office> getSpecification(OfficeInListDto officeInListDto, Organization organization) {
        return (root, query, criteriaBuilder) -> {
            ArrayList<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("organization"), organization));
            if (officeInListDto.getName() != null) {
                predicates.add(criteriaBuilder.equal(root.get("name"), officeInListDto.getName()));
            }
            if (officeInListDto.getIsActive() != null) {
                predicates.add(criteriaBuilder.equal(root.get("isActive"), officeInListDto.getIsActive()));
            }
            if (officeInListDto.getPhone() != null) {
                predicates.add(criteriaBuilder.equal(root.get("phone"), officeInListDto.getPhone()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

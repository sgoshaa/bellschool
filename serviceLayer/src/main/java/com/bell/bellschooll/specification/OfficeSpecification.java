package com.bell.bellschooll.specification;


import com.bell.bellschooll.dto.request.OfficeInListDto;
import com.bell.bellschooll.model.Office;
import com.bell.bellschooll.model.Organization;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

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


    public Specification<Office> getSpecification(Map<String,Object> map, Integer orgId) {
        return (root, query, criteriaBuilder) -> {
            ArrayList<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("organization").get("id"), orgId));

            Set<String> keys = map.keySet();
            for (String key:keys) {
                if (key.equals("name")) {
                    predicates.add(criteriaBuilder.equal(root.get("name"), map.get(key)));
                }
                if (key.equals("isActive")) {
                    predicates.add(criteriaBuilder.equal(root.get("isActive"), map.get(key)));
                }
                if (key.equals("phone")) {
                    predicates.add(criteriaBuilder.equal(root.get("phone"), map.get(key)));
                }
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

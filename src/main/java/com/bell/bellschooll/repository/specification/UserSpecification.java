package com.bell.bellschooll.repository.specification;

import com.bell.bellschooll.dto.request.UserInListDto;
import com.bell.bellschooll.model.Office;
import com.bell.bellschooll.model.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;

/**
 * Класс для создания спецификации для  работы c User
 */
@Component
public class UserSpecification {
    /**
     * меотд для создания спецификации для фильтрации пользователей
     *
     * @param userInListDto объект с параметрами для фильтрации
     * @param office        офис
     * @return Specification<User>
     */
    public Specification<User> getUserSpecification(UserInListDto userInListDto, Office office) {
        return (root, query, criteriaBuilder) -> {
            ArrayList<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("office"), office));

            if (userInListDto.getFirstName() != null) {
                predicates.add(criteriaBuilder.equal(root.get("firstName"), userInListDto.getFirstName()));
            }
            if (userInListDto.getSecondName() != null) {
                predicates.add(criteriaBuilder.equal(root.get("secondName"), userInListDto.getSecondName()));
            }
            if (userInListDto.getMiddleName() != null) {
                predicates.add(criteriaBuilder.equal(root.get("middleName"), userInListDto.getMiddleName()));
            }
            if (userInListDto.getPosition() != null) {
                predicates.add(criteriaBuilder.equal(root.get("position"), userInListDto.getPosition()));
            }
            if (userInListDto.getDocCode() != null) {
                predicates.add(criteriaBuilder.equal(root.get("document").get("docType").get("code"), userInListDto.getDocCode()));
            }
            if (userInListDto.getCitizenshipCode() != null) {
                predicates.add(criteriaBuilder.equal(root.get("country").get("code"), userInListDto.getCitizenshipCode()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

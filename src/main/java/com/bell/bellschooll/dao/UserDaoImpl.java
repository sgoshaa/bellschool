package com.bell.bellschooll.dao;

import com.bell.bellschooll.dto.request.OfficeInListDto;
import com.bell.bellschooll.dto.request.UserInListDto;
import com.bell.bellschooll.model.Office;
import com.bell.bellschooll.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Репозиторий для работы с User, реализация UserDao
 */
@Repository
public class UserDaoImpl implements UserDao {
    private final EntityManager entityManager;

    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * @see UserDao#addUser(User)
     */
    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    /**
     * @see UserDao#updateUser(User)
     */
    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    /**
     * @see UserDao#getUserById(Integer)
     */
    @Override
    public User getUserById(Integer id) {
        return entityManager.find(User.class, id);
    }

    /**
     * @see UserDao#getListUser(UserInListDto, Office)
     */
    @Override
    public List<User> getListUser(UserInListDto userInListDto, Office office) {
        CriteriaQuery<User> userCriteriaQuery = buildCriteriaUser(userInListDto, office);
        TypedQuery<User> query = entityManager.createQuery(userCriteriaQuery);
        return query.getResultList();
    }

    private CriteriaQuery<User> buildCriteriaUser(UserInListDto userInListDto, Office office) {
        String firstName = userInListDto.getFirstName();
        String secondName = userInListDto.getSecondName();
        String middleName = userInListDto.getMiddleName();
        String position = userInListDto.getPosition();
        String docCode = userInListDto.getDocCode();
        String citizenshipCode = userInListDto.getCitizenshipCode();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> officeCriteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = officeCriteriaQuery.from(User.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(criteriaBuilder.equal(root.get("office"), office));
        if (firstName != null && !firstName.isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("firstName"), firstName));
        }
        if (secondName != null && !secondName.isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("secondName"), secondName));
        }
        if (middleName != null && !middleName.isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("middleName"), middleName));
        }
        if (position != null && !position.isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("position"), position));
        }
        if (docCode != null && !docCode.isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("document").get("docType").get("code"), docCode));
        }
        if (citizenshipCode != null && !citizenshipCode.isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("country").get("code"), citizenshipCode));
        }
        return officeCriteriaQuery.where(predicates.toArray(new Predicate[0]));
    }
}

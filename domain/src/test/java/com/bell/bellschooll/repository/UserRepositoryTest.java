package com.bell.bellschooll.repository;


import com.bell.bellschooll.model.User;
import com.bell.bellschooll.util.UserHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DocumentTypeRepository documentTypeRepository;

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    OfficeRepository officeRepository;

    @Test
    @DisplayName("Метод проверяет сохранение нового пользователя в БД")
    void addUser() {
        //Given
        User newUser = UserHelper.createUser(
                documentTypeRepository.getDocumentTypeByCode("21").get(),
                officeRepository.findById(1).get(),
                countryRepository.getCountryByCode("643").get()
        );

        //When
        User savedUser = userRepository.save(newUser);

        //Then
        User userById = userRepository.getById(savedUser.getId());

        assertNotNull(userById);
        assertEquals(savedUser.getFirstName(), userById.getFirstName());
        assertEquals(savedUser.getMiddleName(), userById.getMiddleName());
        assertEquals(savedUser.getSecondName(), userById.getSecondName());
        assertEquals(savedUser.getDocument().getNumber(), userById.getDocument().getNumber());
        assertEquals(savedUser.getDocument().getDate(), userById.getDocument().getDate());
        assertEquals(savedUser.getDocument().getDocType(), userById.getDocument().getDocType());

    }

    @Test
    @DisplayName("Метод проверяет обновление пользователя с ID = 1 ")
    void updateUser() {
        //Given
        User userById = userRepository.findById(1).get();
        userById.setFirstName("UpdateFirstName");
        userById.getDocument().setNumber("789456123");

        //When
        User updatedUser = userRepository.save(userById);

        //Then
        User byId = userRepository.getById(updatedUser.getId());
        assertNotNull(byId);
        assertEquals(updatedUser.getFirstName(), byId.getFirstName());
        assertEquals(updatedUser.getDocument().getNumber(), byId.getDocument().getNumber());
        assertEquals(updatedUser, byId);
    }

    @Test
    @DisplayName("Метод проверяет получение пользователя из репозитория по ID = 1")
    void getUserById() {
        //When
        Optional<User> userById = userRepository.findById(1);

        //Then
        assertTrue(userById.isPresent());
        assertEquals(1, userById.get().getId());
    }

    @ParameterizedTest
    @DisplayName("Метод проверяет findAll() с обязательными полями")
    @CsvSource({
            "1,,,,,,",
            "1,,,,,,643",
            "1,Спирин,,,,,643",
            "1,Спирин,Игорь,,,,643",
            "1,Спирин,Игорь,Александрович,,,643"
    })
    void getListUser(Integer officeId, String firstName, String secondName
            , String middleName, String position, String docCode, String countryCode) {

        //When
        List<User> listUser = userRepository
                .findAll(getUserSpecification(officeId, firstName, secondName, middleName, position, docCode, countryCode));

        //Then
        assertFalse(listUser.isEmpty());
        listUser.forEach(user -> {
            assertEquals(user.getOffice().getId(), officeId);
        });
        assertThat(listUser.stream().map(User::getFirstName).collect(Collectors.toList()), hasItem("Спирин"));
    }

    private Specification<User> getUserSpecification(Integer officeId, String firstName, String secondName
            , String middleName, String position, String docCode, String countryCode) {
        return (root, query, criteriaBuilder) -> {
            ArrayList<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("office").get("id"), officeId));
            if (firstName != null) {
                predicates.add(criteriaBuilder.equal(root.get("firstName"), firstName));
            }
            if (secondName != null) {
                predicates.add(criteriaBuilder.equal(root.get("secondName"), secondName));
            }
            if (middleName != null) {
                predicates.add(criteriaBuilder.equal(root.get("middleName"), middleName));
            }
            if (position != null) {
                predicates.add(criteriaBuilder.equal(root.get("position"), position));
            }
            if (docCode != null) {
                predicates.add(criteriaBuilder.equal(root.get("document").get("docType").get("code"), docCode));
            }
            if (countryCode != null) {
                predicates.add(criteriaBuilder.equal(root.get("country").get("code"), countryCode));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
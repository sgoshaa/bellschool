package com.bell.bellschooll.repository;

import com.bell.bellschooll.dto.request.UserInListDto;
import com.bell.bellschooll.model.Document;
import com.bell.bellschooll.model.User;
import com.bell.bellschooll.repository.specification.UserSpecification;
import com.bell.bellschooll.util.ConstantValue;
import com.bell.bellschooll.util.UserHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.*;

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

    @Autowired
    UserSpecification userSpecification;

    @Test
    @DisplayName("Метод проверяет сохранение нового пользователя в БД")
    void addUser() {
        //Given
        User newUser = UserHelper.createUser();

        Document document = new Document();
        document.setUser(newUser);
        document.setDocType(documentTypeRepository.getDocumentTypeByCode("21").get());
        document.setDate(LocalDate.now());
        document.setNumber("12120 322323");

        newUser.setDocument(document);
        newUser.setCountry(countryRepository.getCountryByCode("643").get());
        newUser.setOffice(officeRepository.getById(ConstantValue.ID));

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
        User userById = userRepository.getById(ConstantValue.ID);
        userById.setFirstName("UpdateFirstName");
        userById.getDocument().setNumber("789456123");

        //When
        User updatedUser = userRepository.save(userById);

        //Then
        User byId = userRepository.getById(updatedUser.getId());
        assertNotNull(byId);
        assertEquals(updatedUser.getFirstName(), byId.getFirstName());
        assertEquals(updatedUser.getDocument().getNumber(), byId.getDocument().getNumber());
    }

    @Test
    @DisplayName("Метод проверяет получение пользователя из репозитория по ID = 1")
    void getUserById() {
        //When
        User userById = userRepository.getById(ConstantValue.ID);

        //Then
        assertNotNull(userById);
        assertEquals(ConstantValue.ID, userById.getId());
    }

    @Test
    @DisplayName("Метод проверяет findAll() с обязательными полями")
    void getListUser() {
        //Given
        UserInListDto userInListDto = UserHelper.createUserInListDto();

        //When
        List<User> listUser = userRepository
                .findAll(userSpecification.getUserSpecification(userInListDto, officeRepository.getById(ConstantValue.ID)));

        //Then
        assertFalse(listUser.isEmpty());
        listUser.forEach(user -> {
            assertEquals(user.getOffice().getId(), userInListDto.getOfficeId());
            assertEquals(user.getCountry().getCode().toString(), userInListDto.getCitizenshipCode());
        });
    }

    @Test
    void getListUserByFirstName() {
        //Given
        UserInListDto userInListDto = UserHelper.createUserInListDto();
        userInListDto.setFirstName("Иванов");

        //When
        List<User> listUser = userRepository
                .findAll(userSpecification.getUserSpecification(userInListDto
                        , officeRepository.getById(ConstantValue.ID)));

        //Then
        assertFalse(listUser.isEmpty());
        assertThat(listUser.stream()
                        .map(User::getFirstName)
                        .collect(Collectors.toList())
                , hasItem(userInListDto.getFirstName()));
    }

    @Test
    @DisplayName("Метод проверяет работу метода findAll() с параметрами FirstName and SecondName")
    void getListUserByFirstAndLastName() {
        //Given
        UserInListDto userInListDto = UserHelper.createUserInListDto();
        userInListDto.setFirstName("Иванов");
        userInListDto.setSecondName("Иван");

        //When
        List<User> listUser = userRepository.findAll(userSpecification
                .getUserSpecification(userInListDto, officeRepository.getById(ConstantValue.ID)));

        //Then
        assertFalse(listUser.isEmpty());
        listUser.forEach(tempUser -> {
            assertEquals(tempUser.getFirstName(), userInListDto.getFirstName());
            assertEquals(tempUser.getSecondName(), userInListDto.getSecondName());
        });
    }

    @Test
    @DisplayName("Метод проверяет работу метода findAll() со всеми доступными параметрами")
    void getListUserByAllField() {
        //Given
        UserInListDto userInListDto = UserHelper.createUserInListDto();
        userInListDto.setFirstName("Иванов");
        userInListDto.setSecondName("Иван");
        userInListDto.setMiddleName("Иванович");
        userInListDto.setPosition("водитель");
        userInListDto.setDocCode("22");

        //When
        List<User> listUser = userRepository.findAll(userSpecification
                .getUserSpecification(userInListDto, officeRepository.getById(ConstantValue.ID)));

        //Then
        assertFalse(listUser.isEmpty());

        listUser.forEach(tempUser -> {
            assertEquals(tempUser.getFirstName(), userInListDto.getFirstName());
            assertEquals(tempUser.getSecondName(), userInListDto.getSecondName());
            assertEquals(tempUser.getMiddleName(), userInListDto.getMiddleName());
            assertEquals(tempUser.getPosition(), userInListDto.getPosition());
            assertEquals(tempUser.getDocument().getDocType().getCode(), userInListDto.getDocCode());
        });
    }
}
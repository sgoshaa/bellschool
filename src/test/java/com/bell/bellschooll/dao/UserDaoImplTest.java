package com.bell.bellschooll.dao;

import com.bell.bellschooll.dto.request.UserInListDto;
import com.bell.bellschooll.model.Document;
import com.bell.bellschooll.model.Office;
import com.bell.bellschooll.model.User;
import com.bell.bellschooll.util.ConstantValue;
import com.bell.bellschooll.util.UserHelper;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
class UserDaoImplTest {

    @Autowired
    UserDao userDao;

    @Autowired
    DocumentTypeDao documentTypeDao;

    @Autowired
    CountryDao countryDao;

    @Autowired
    OfficeDao officeDao;

    @Test
    void addUser() {
        User newUser = UserHelper.createUser();

        Document document = new Document();
        document.setUser(newUser);
        document.setDocType(documentTypeDao.getDocumentTypeByCode("21"));
        document.setDocDate(LocalDate.now());
        document.setDocNumber("12120 322323");

        newUser.setDocument(document);
        newUser.setCountry(countryDao.getCountryByCode(643));
        newUser.setOffice(officeDao.getOfficeById(ConstantValue.ID));
        userDao.addUser(newUser);

        User userById = userDao.getUserById(newUser.getId());

        assertNotNull(userById);
        assertEquals(newUser.getFirstName(), userById.getFirstName());
        assertEquals(newUser.getMiddleName(), userById.getMiddleName());
        assertEquals(newUser.getSecondName(), userById.getSecondName());

    }

    @Test
    void updateUser() {
        User userById = userDao.getUserById(ConstantValue.ID);
        assertNotNull(userById);

        userById.setFirstName("UpdateFirstName");
        userDao.updateUser(userById);

        User updateUser = userDao.getUserById(userById.getId());
        assertNotNull(updateUser);
        assertEquals(userById.getFirstName(), updateUser.getFirstName());
    }

    @Test
    void getUserById() {
        User userById = userDao.getUserById(ConstantValue.ID);
        assertNotNull(userById);
    }

    @Test
    void getListUser() {
        UserInListDto userInListDto = UserHelper.createUserInListDto();
        List<User> listUser = userDao.getListUser(userInListDto, officeDao.getOfficeById(ConstantValue.ID));
        assertFalse(listUser.isEmpty());
    }

    @Test
    void getListUserFirstName() {
        UserInListDto userInListDto = UserHelper.createUserInListDto();
        userInListDto.setFirstName("Иванов");
        List<User> listUser = userDao.getListUser(userInListDto, officeDao.getOfficeById(ConstantValue.ID));
        assertFalse(listUser.isEmpty());

        assertThat(listUser.stream()
                        .map(User::getFirstName)
                        .collect(Collectors.toList())
                , hasItem(userInListDto.getFirstName()));
    }

    @Test
    void getListUserFirstAndLastName() {
        UserInListDto userInListDto = UserHelper.createUserInListDto();
        userInListDto.setFirstName("Иванов");
        userInListDto.setSecondName("Иван");
        List<User> listUser = userDao.getListUser(userInListDto, officeDao.getOfficeById(ConstantValue.ID));
        assertFalse(listUser.isEmpty());

        User tempUser = listUser.stream()
                .filter(user -> user.getFirstName().equals(userInListDto.getFirstName()))
                .filter(user -> user.getSecondName().equals(userInListDto.getSecondName()))
                .findFirst()
                .get();

        assertEquals(tempUser.getFirstName(), userInListDto.getFirstName());
        assertEquals(tempUser.getSecondName(), userInListDto.getSecondName());
    }

    @Test
    void getListUserAllField() {
        UserInListDto userInListDto = UserHelper.createUserInListDto();
        userInListDto.setFirstName("Иванов");
        userInListDto.setSecondName("Иван");
        userInListDto.setMiddleName("Иванович");
        userInListDto.setPosition("водитель");
        userInListDto.setDocCode("22");
        List<User> listUser = userDao.getListUser(userInListDto, officeDao.getOfficeById(ConstantValue.ID));
        assertFalse(listUser.isEmpty());

        User tempUser = listUser.stream()
                .findFirst()
                .get();

        assertEquals(tempUser.getFirstName(), userInListDto.getFirstName());
        assertEquals(tempUser.getSecondName(), userInListDto.getSecondName());
        assertEquals(tempUser.getMiddleName(), userInListDto.getMiddleName());
        assertEquals(tempUser.getPosition(), userInListDto.getPosition());
        assertEquals(tempUser.getDocument().getDocType().getCode(), userInListDto.getDocCode());
    }
}
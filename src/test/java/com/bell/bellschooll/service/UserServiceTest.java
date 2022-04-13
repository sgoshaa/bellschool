package com.bell.bellschooll.service;

import com.bell.bellschooll.dto.request.UpdateUserInDto;
import com.bell.bellschooll.dto.response.SuccessDto;
import com.bell.bellschooll.dto.response.UserOutDto;
import com.bell.bellschooll.dto.response.UserOutListDto;
import com.bell.bellschooll.exception.ErrorException;
import com.bell.bellschooll.util.ConstantValue;
import com.bell.bellschooll.util.UserHelper;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    void addUser() {
        ResponseEntity<SuccessDto> successDtoResponseEntity = userService.addUser(UserHelper.createUserInSaveDto());
        assertEquals(ConstantValue.RESULT, successDtoResponseEntity.getBody().getResult());
    }

    @Test
    void addUserFailDocumentType() {
        assertThrows(ErrorException.class, () -> userService.addUser(UserHelper.createUserInSaveDto("1005")));
    }

    @Test
    void addUserFailCountryCode() {
        assertThrows(ErrorException.class, () -> userService.addUser(UserHelper.createUserInSaveDto(1005)));
    }

    @Test
    void getUser() {
        ResponseEntity<UserOutDto> user = userService.getUser(ConstantValue.ID);
        assertNotNull(user);
        assertEquals(ConstantValue.ID, Objects.requireNonNull(user.getBody()).getId());
    }

    @Test
    void getUserFail() {
        assertThrows(ErrorException.class, () -> userService.getUser(10005));
    }

    @Test
    void getListUser() {
        List<UserOutListDto> listUser = userService.getListUser(UserHelper.createUserInListDto()).getBody();
        assertNotNull(listUser);
        assertTrue(listUser.size() > 0);
        UserOutListDto userOutListDto = listUser.stream().findFirst().get();
        assertThat(userOutListDto,hasProperty("firstName",equalTo("Спирин")));
    }

    @Test
    void updateUser() {
        UpdateUserInDto updateUserInDto = UserHelper.createUpdateUserInDto();
        ResponseEntity<SuccessDto> successDtoResponseEntity = userService.updateUser(updateUserInDto);
        assertEquals(ConstantValue.RESULT, Objects.requireNonNull(successDtoResponseEntity.getBody()).getResult());

        UserOutDto user = userService.getUser(updateUserInDto.getId()).getBody();

        assert user != null;

        assertEquals(updateUserInDto.getId(), user.getId());
        assertEquals(updateUserInDto.getFirstName(), user.getFirstName());
        assertEquals(updateUserInDto.getPosition(), user.getPosition());
    }
}
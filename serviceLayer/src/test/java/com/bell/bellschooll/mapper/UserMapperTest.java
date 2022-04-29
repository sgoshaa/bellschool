package com.bell.bellschooll.mapper;

import com.bell.bellschooll.util.UserHelper;
import com.bell.bellschooll.dto.request.UserInSaveDto;
import com.bell.bellschooll.dto.response.UserOutDto;
import com.bell.bellschooll.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    void dtoToDomain() {
        //Given
        UserInSaveDto userInSaveDto = UserHelper.createUserInSaveDto();
        User user = UserHelper.createUser();

        //When
        User userActual = userMapper.dtoToDomain(userInSaveDto);

        //Then
        assertEquals(user.getFirstName(), userActual.getFirstName());
        assertEquals(user.getSecondName(), userActual.getSecondName());
        assertEquals(user.getMiddleName(), userActual.getMiddleName());
        assertEquals(user.getPosition(), userActual.getPosition());
        assertEquals(user.getPhone(), userActual.getPhone());
    }

    @Test
    void domainToDto() {
        //Given
        UserOutDto userOutDto = UserHelper.createUserOutDto();
        User user = UserHelper.createUser();

        //When
        UserOutDto userOutDtoActual = userMapper.domainToDto(user);

        //Then
        assertEquals(userOutDto.getFirstName(), userOutDtoActual.getFirstName());
        assertEquals(userOutDto.getSecondName(), userOutDtoActual.getSecondName());
        assertEquals(userOutDto.getMiddleName(), userOutDtoActual.getMiddleName());
        assertEquals(userOutDto.getPosition(), userOutDtoActual.getPosition());
        assertEquals(userOutDto.getPhone(), userOutDtoActual.getPhone());
    }

    @Test
    void domainToOutListDto() {
    }

    @Test
    void updateUserFromDto() {
    }

    @Test
    void toListDto() {
    }
}
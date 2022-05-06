package com.bell.bellschooll.mapper;

import com.bell.bellschooll.dto.request.UpdateUserInDto;
import com.bell.bellschooll.dto.request.UserInSaveDto;
import com.bell.bellschooll.dto.response.UserOutDto;
import com.bell.bellschooll.dto.response.UserOutListDto;
import com.bell.bellschooll.model.User;
import com.bell.bellschooll.util.ConstantValue;
import com.bell.bellschooll.util.UserHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = UserMapper.class)
@ContextConfiguration(classes = MapperTestConfig.class)
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
        assertEquals(user, userActual);
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
        //Given
        User user = UserHelper.createUser();

        UserOutListDto userOutListDtoExp = new UserOutListDto();
        userOutListDtoExp.setSecondName(user.getSecondName());
        userOutListDtoExp.setFirstName(user.getFirstName());
        userOutListDtoExp.setId(user.getId());
        userOutListDtoExp.setMiddleName(user.getMiddleName());
        userOutListDtoExp.setPosition(user.getPosition());

        //When
        UserOutListDto userOutListDto = userMapper.domainToOutListDto(user);

        //Then
        assertEquals(userOutListDtoExp, userOutListDto);
    }

    @Test
    void updateUserFromDto() {
        //Given
        UpdateUserInDto updateUserInDto = UserHelper.createUpdateUserInDto();
        User userExp = UserHelper.createUser();

        //When
        User userActual = userMapper.updateUserFromDto(updateUserInDto, userExp);

        //Then
        userExp.setId(updateUserInDto.getId());
        userExp.setPhone(updateUserInDto.getPhone());
        userExp.setPosition(updateUserInDto.getPosition());
        userExp.setFirstName(updateUserInDto.getFirstName());
        userExp.setSecondName(updateUserInDto.getSecondName());
        userExp.setMiddleName(updateUserInDto.getMiddleName());
        userExp.setIsIdentified(updateUserInDto.getIsIdentified());

        assertEquals(userExp, userActual);
    }

    @Test
    void toListDto() {
        //Given
        User user = UserHelper.createUser();
        user.setId(ConstantValue.ID);
        List<UserOutListDto> listUserOutDto = UserHelper.createListUserOutDto();

        //When
        List<UserOutListDto> dtoList = userMapper.toListDto(List.of(user));

        //Then
        assertEquals(listUserOutDto, dtoList);
    }
}
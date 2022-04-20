package com.bell.bellschooll.controller;

import com.bell.bellschooll.dto.request.UpdateUserInDto;
import com.bell.bellschooll.dto.request.UserInListDto;
import com.bell.bellschooll.dto.request.UserInSaveDto;
import com.bell.bellschooll.dto.response.ResponseDto;
import com.bell.bellschooll.dto.response.SuccessDto;
import com.bell.bellschooll.dto.response.UserOutDto;
import com.bell.bellschooll.dto.response.UserOutListDto;
import com.bell.bellschooll.service.UserService;
import com.bell.bellschooll.util.ConstantValue;
import com.bell.bellschooll.util.UserHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.coyote.http11.upgrade.UpgradeServletOutputStream;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    public static final String API_USER = "/api/user/";

    @MockBean
    UserService userService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("Проверка метода контроллера addUser() с  userInSaveDto")
    void addUser() throws Exception {
        //Given
        UserInSaveDto userInSaveDto = UserHelper.createUserInSaveDto();
        ResponseDto responseDto = new ResponseDto();
        SuccessDto data = new SuccessDto();
        responseDto.setData(data);

        when(userService.addUser(userInSaveDto)).thenReturn(new ResponseEntity<SuccessDto>(data, HttpStatus.OK));

        //When
        mockMvc.perform(post(API_USER + "save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userInSaveDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(responseDto)));
    }

    @Test
    @DisplayName("Проверка метода контроллера  getUser() c id = 1")
    void getUser() throws Exception {
        //Given
        UserOutDto userOutDto = UserHelper.createUserOutDto();
        ResponseEntity<UserOutDto> userOutDtoResponseEntity = new ResponseEntity<>(userOutDto, HttpStatus.OK);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setData(userOutDto);

        when(userService.getUser(ConstantValue.ID)).thenReturn(userOutDtoResponseEntity);

        //When
        mockMvc.perform(get(API_USER + ConstantValue.ID))
                //Then
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(responseDto)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Проверка работоспособности метода getListUser() с userInListDto ")
    void getListUser() throws Exception {
        //Given
        UserInListDto userInListDto = UserHelper.createUserInListDto();
        List<UserOutListDto> listUserOutDto = UserHelper.createListUserOutDto();
        ResponseEntity<List<UserOutListDto>> listResponseEntity = new ResponseEntity<>(listUserOutDto, HttpStatus.OK);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setData(listUserOutDto);

        when(userService.getListUser(userInListDto)).thenReturn(listResponseEntity);

        //When
        mockMvc.perform(post(API_USER + "list")
                        .content(objectMapper.writeValueAsString(userInListDto))
                        .contentType(MediaType.APPLICATION_JSON))
                //Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(responseDto)));
    }

    @Test
    @DisplayName("Проверка метода  updateUser() с updateUserInDto")
    void updateUser() throws Exception {
        //Given
        UpdateUserInDto updateUserInDto = UserHelper.createUpdateUserInDto();
        SuccessDto successDto = new SuccessDto();
        ResponseDto responseDto = new ResponseDto();
        responseDto.setData(successDto);

        when(userService.updateUser(updateUserInDto)).thenReturn(new ResponseEntity<SuccessDto>(successDto, HttpStatus.OK));

        //When
        mockMvc.perform(post(API_USER + "update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateUserInDto)))
                //Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(responseDto)));
    }
}
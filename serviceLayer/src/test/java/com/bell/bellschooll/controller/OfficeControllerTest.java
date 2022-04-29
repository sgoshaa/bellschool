package com.bell.bellschooll.controller;

import com.bell.bellschooll.util.ConstantValue;
import com.bell.bellschooll.util.OfficeHelper;
import com.bell.bellschooll.dto.request.OfficeInListDto;
import com.bell.bellschooll.dto.request.OfficeInSaveDto;
import com.bell.bellschooll.dto.request.OfficeInUpdateDto;
import com.bell.bellschooll.dto.response.OfficeListOutDto;
import com.bell.bellschooll.dto.response.OfficeOutDto;
import com.bell.bellschooll.dto.response.ResponseDto;
import com.bell.bellschooll.dto.response.SuccessDto;
import com.bell.bellschooll.service.OfficeService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(OfficeController.class)
class OfficeControllerTest {

    public static final String API_OFFICE = "/api/office/";
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    OfficeService officeService;

    @Test
    @DisplayName("Проверка работоспособности метода getOfficeById()")
    void getById() throws Exception {
        //Given
        OfficeOutDto officeOutDto = OfficeHelper.createOfficeOutDto();
        ResponseDto responseDto = new ResponseDto();
        responseDto.setData(officeOutDto);
        ResponseEntity<OfficeOutDto> officeOutDtoResponseEntity = new ResponseEntity<>(officeOutDto, HttpStatus.OK);
        when(officeService.getOfficeById(ConstantValue.ID)).thenReturn(officeOutDtoResponseEntity);
        //When
        mockMvc.perform(
                        get(API_OFFICE + ConstantValue.ID)
                                .content(objectMapper.writeValueAsString(ConstantValue.ID)))
                //Then
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(responseDto)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Проверка метода addOffice(officeInSaveDto)")
    void addOffice() throws Exception {
        //Given
        OfficeInSaveDto officeInSaveDto = OfficeHelper.createOfficeInSaveDto();
        ResponseDto responseDto = new ResponseDto();
        SuccessDto data = new SuccessDto();
        responseDto.setData(data);
        when(officeService.addOffice(officeInSaveDto)).thenReturn(new ResponseEntity<>(data, HttpStatus.OK));

        //When
        mockMvc.perform(post(API_OFFICE + "save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(officeInSaveDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(responseDto)));
    }

    @Test
    @DisplayName("Проверка метода ListOffice(officeInListDto) по officeInListDto  ")
    void listOffice() throws Exception {
        //Given
        OfficeInListDto officeInListDto = OfficeHelper.createOfficeInListDto();
        List<OfficeListOutDto> officeListOutDto = OfficeHelper.createOfficeListOutDto();
        ResponseEntity<List<OfficeListOutDto>> listResponseEntity = new ResponseEntity<>(officeListOutDto, HttpStatus.OK);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setData(officeListOutDto);

        when(officeService.getListOffice(officeInListDto)).thenReturn(listResponseEntity);

        //When
        mockMvc.perform(post(API_OFFICE + "list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(officeInListDto)))
                //Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(responseDto)));
    }

    @Test
    @DisplayName("Проверка метода контроллера updateOffice() с officeInUpdateDto ")
    void updateOffice() throws Exception {
        //Given
        OfficeInUpdateDto officeInUpdateDto = OfficeHelper.createOfficeInUpdateDto();
        SuccessDto successDto = new SuccessDto();
        ResponseDto responseDto = new ResponseDto();
        responseDto.setData(successDto);

        when(officeService.updateOffice(officeInUpdateDto)).thenReturn(new ResponseEntity<SuccessDto>(successDto, HttpStatus.OK));

        //When
        mockMvc.perform(post(API_OFFICE + "update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(officeInUpdateDto)))
                //Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(responseDto)));
    }
}
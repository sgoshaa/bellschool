package com.bell.bellschooll.controller;

import com.bell.bellschooll.util.ConstantValue;
import com.bell.bellschooll.util.OrganizationHelper;
import com.bell.bellschooll.dto.request.OrganisationDtoRequest;
import com.bell.bellschooll.dto.request.OrganizationSaveInDto;
import com.bell.bellschooll.dto.request.OrganizationUpdateInDto;
import com.bell.bellschooll.dto.response.OrganizationListOut;
import com.bell.bellschooll.dto.response.OrganizationOutDto;
import com.bell.bellschooll.dto.response.ResponseDto;
import com.bell.bellschooll.dto.response.SuccessDto;
import com.bell.bellschooll.service.OrganizationService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrganizationController.class)
class OrganizationControllerTest {

    static String URL = "/api/organization/";

    @MockBean
    OrganizationService organizationService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Test
    void getListOrganization() throws Exception {
        //Given
        OrganisationDtoRequest organisationDtoRequest = OrganizationHelper.createOrganisationDtoRequest();

        List<OrganizationListOut> organizationListOutDto = List.of(OrganizationHelper.createOrganizationListOutDto());
        ResponseEntity<List<OrganizationListOut>> organizationListOutResponseEntity
                = new ResponseEntity<>(organizationListOutDto, HttpStatus.OK);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setData(organizationListOutDto);

        when(organizationService.getOrganizationByOrganizationDtoRequest(organisationDtoRequest))
                .thenReturn(organizationListOutResponseEntity);
        //When
        mockMvc.perform(post(URL + "list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(organisationDtoRequest)))
               //Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(responseDto)));

    }

    @Test
    void getOrganizationById() throws Exception {
        //Given
        OrganizationOutDto organizationOutDto = OrganizationHelper.createOrganizationOutDto();
        organizationOutDto.setId(ConstantValue.ID);

        ResponseEntity<OrganizationOutDto> responseEntity = new ResponseEntity<>(organizationOutDto, HttpStatus.OK);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setData(responseEntity.getBody());

        when(organizationService.getOrganizationById(ConstantValue.ID)).thenReturn(responseEntity);
        //When
        mockMvc.perform(get(URL + ConstantValue.ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ConstantValue.ID)))
                //Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(responseDto)));
    }

    @Test
    void addOrganization() throws Exception {
        //Given
        OrganizationSaveInDto organizationSaveInDto = OrganizationHelper.createOrganizationSaveInDto();
        SuccessDto successDto = new SuccessDto();
        ResponseEntity<SuccessDto> successDtoResponseEntity = new ResponseEntity<>(successDto, HttpStatus.OK);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setData(successDtoResponseEntity.getBody());

        when(organizationService.addOrganization(organizationSaveInDto)).thenReturn(successDtoResponseEntity);

        //When
        mockMvc.perform(
                        post(URL + "save")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(organizationSaveInDto)))
                //Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(responseDto)));
    }

    @Test
    void updateOrganization() throws Exception {
        //Given
        OrganizationUpdateInDto organizationUpdateInDto = OrganizationHelper.createOrganizationUpdateInDto();
        organizationUpdateInDto.setPhone("2-57-05");
        ResponseEntity<SuccessDto> successDtoResponseEntity = new ResponseEntity<>(new SuccessDto(), HttpStatus.OK);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setData(successDtoResponseEntity.getBody());
        when(organizationService.updateOrganization(organizationUpdateInDto)).thenReturn(successDtoResponseEntity);

        //When
        mockMvc.perform(post(URL + "update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(organizationUpdateInDto)))
                //Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(responseDto))
                );
    }
}
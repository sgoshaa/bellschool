package com.bell.bellschooll.controller;

import com.bell.bellschooll.dto.response.CountryDto;
import com.bell.bellschooll.dto.response.ResponseDto;
import com.bell.bellschooll.service.CountryService;
import com.bell.bellschooll.util.CountryHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;



import java.util.List;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(CountryController.class)
class CountryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryService countryService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllCountry() throws Exception {
        //Given
        CountryDto countryDto = CountryHelper.createCountryDto();
        countryDto.setName("first");

        List<CountryDto> countryList = List.of(countryDto);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setData(countryList);

        when(countryService.getAllCountry()).thenReturn(countryList);
        //When
        mockMvc.perform(
                        get("/api/countries"))
                //Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(responseDto)));
        verify(countryService).getAllCountry();
    }
}
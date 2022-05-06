package com.bell.bellschooll.service;

import com.bell.bellschooll.dto.response.CountryDto;
import com.bell.bellschooll.mapper.CountryMapper;
import com.bell.bellschooll.mapper.CountryMapperImpl;
import com.bell.bellschooll.mapper.MapperTestConfig;
import com.bell.bellschooll.model.Country;
import com.bell.bellschooll.repository.CountryRepository;
import com.bell.bellschooll.util.CountryHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {CountryServiceImpl.class, CountryMapperImpl.class})
class CountryServiceImplTest {

    @MockBean
    CountryRepository countryRepository;

    @Autowired
    CountryService countryService;

    @Test
    void getAllCountry() {
        //Given
        Country country = CountryHelper.createCountry();
        when(countryRepository.findAll()).thenReturn(List.of(country));

        //When
        List<CountryDto> allCountry = countryService.getAllCountry();

        //Then
        assertNotNull(allCountry);
        List<String> stringList = allCountry.stream()
                .map(CountryDto::getCode)
                .collect(Collectors.toList());
        assertThat(stringList, hasItem("650"));
        verify(countryRepository).findAll();
    }

    @Test
    void getCountryByCode() {
        //Given
        Country country = CountryHelper.createCountry();
        String code = "650";
        when(countryRepository.getCountryByCode(code)).thenReturn(Optional.of(country));

        //When
        Country countryByCode = countryService.getCountryByCode(code);

        //Then
        assertEquals(country, countryByCode);
        verify(countryRepository).getCountryByCode(code);
    }
}
package com.bell.bellschooll.mapper;

import com.bell.bellschooll.dto.response.CountryDto;
import com.bell.bellschooll.model.Country;
import com.bell.bellschooll.util.CountryHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class CountryMapperTest {

    @Autowired
    CountryMapper countryMapper;

    @Test
    void toDto() {
        //Given
        Country country = CountryHelper.createCountry();
        CountryDto countryDto = CountryHelper.createCountryDto();

        //When
        CountryDto countryDtoActual = countryMapper.toDto(country);

        //Then
        assertEquals(countryDto.getName(), countryDtoActual.getName());
        assertEquals(countryDto.getCode(), countryDtoActual.getCode());
    }

    @Test
    void toListDto() {
        //Given
        List<Country> listCountry = CountryHelper.createListCountry();
        List<CountryDto> countryDtoList = CountryHelper.createListCountryDto();

        //When
        List<CountryDto> countryDtoListActual = countryMapper.toListDto(listCountry);

        //Then
        assertEquals(countryDtoList.size(), countryDtoListActual.size());
        assertThat(countryDtoListActual.stream()
                        .map(CountryDto::getName)
                        .collect(Collectors.toList())
                , hasItem(listCountry.stream()
                        .map(Country::getName)
                        .findFirst().get()));

    }
}
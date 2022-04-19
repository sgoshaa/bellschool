package com.bell.bellschooll.util;

import com.bell.bellschooll.dto.response.CountryDto;

public class CountryHelper {
    public static CountryDto createCountryDto() {
        CountryDto countryDto = new CountryDto();
        countryDto.setName("Страна");
        countryDto.setCode("650");
        return countryDto;
    }
}

package com.bell.bellschooll.util;

import com.bell.bellschooll.dto.response.CountryDto;
import com.bell.bellschooll.model.Country;

import java.util.ArrayList;
import java.util.List;

public class CountryHelper {
    public static CountryDto createCountryDto() {
        Country country = createCountry();
        CountryDto countryDto = new CountryDto();
        countryDto.setName(country.getName());
        countryDto.setCode(country.getCode());
        return countryDto;
    }

    public static Country createCountry() {
        Country country = new Country();
        country.setName("Страна");
        country.setId(ConstantValue.ID);
        country.setCode("650");
        return country;
    }

    public static List<CountryDto> createListCountryDto() {
        ArrayList<CountryDto> countryDtoArrayList = new ArrayList<>();
        countryDtoArrayList.add(createCountryDto());
        return countryDtoArrayList;
    }

    public static List<Country> createListCountry() {
        ArrayList<Country> countryList = new ArrayList<>();
        countryList.add(createCountry());
        return countryList;
    }
}

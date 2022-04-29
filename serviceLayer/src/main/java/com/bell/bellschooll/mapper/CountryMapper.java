package com.bell.bellschooll.mapper;

import com.bell.bellschooll.dto.response.CountryDto;
import com.bell.bellschooll.model.Country;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    CountryDto toDto(Country country);

    List<CountryDto> toListDto(List<Country> countryList);
}

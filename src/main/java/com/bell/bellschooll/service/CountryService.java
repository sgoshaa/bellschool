package com.bell.bellschooll.service;

import com.bell.bellschooll.dto.response.CountryDto;
import com.bell.bellschooll.model.Country;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс для работы с Country
 */
public interface CountryService {
    /**
     * Метод возвращает все страны из справочника Country
     *
     * @return List CountryDto
     */
    List<CountryDto> getAllCountry();

   Country getCountryByCode(String code);
}

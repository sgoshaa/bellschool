package com.bell.bellschooll.service;

import com.bell.bellschooll.exception.anyUserErrorException;
import com.bell.bellschooll.repository.CountryRepository;
import com.bell.bellschooll.dto.response.CountryDto;
import com.bell.bellschooll.mapper.CountryMapper;
import com.bell.bellschooll.model.Country;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для работы с Country
 */
@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    public CountryServiceImpl(CountryRepository countryRepository, CountryMapper countryMapper) {
        this.countryRepository = countryRepository;
        this.countryMapper = countryMapper;
    }

    /**
     * @see CountryService#getAllCountry()
     */
    public List<CountryDto> getAllCountry() {
        List<Country> countryList = countryRepository.findAll();
        return countryMapper.toListDto(countryList);
    }

    @Override
    public Country getCountryByCode(String code) {
        return countryRepository.getCountryByCode(code)
                .orElseThrow(() -> new anyUserErrorException("Не найдена страна по данному коду."));
    }
}

package com.bell.bellschooll.service;

import com.bell.bellschooll.dao.CountryDao;
import com.bell.bellschooll.dto.response.CountryDto;
import com.bell.bellschooll.mapper.CountryMapper;
import com.bell.bellschooll.model.Country;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryDao countryDao;
    private final CountryMapper countryMapper;

    public CountryServiceImpl(CountryDao countryDao, CountryMapper countryMapper) {
        this.countryDao = countryDao;
        this.countryMapper = countryMapper;
    }

    public List<CountryDto> getAllCountry() {
        List<Country> countryList = countryDao.findAll();
        return countryMapper.toListDto(countryList);
    }
}

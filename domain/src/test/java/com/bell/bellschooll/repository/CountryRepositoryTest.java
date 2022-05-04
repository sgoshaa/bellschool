package com.bell.bellschooll.repository;

import com.bell.bellschooll.model.Country;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class CountryRepositoryTest {

    @Autowired
    CountryRepository countryRepository;

    @Test
    void getCountryByCode() {
        String code = "643";
        Optional<Country> countryByCode = countryRepository.getCountryByCode(code);
        Country country = countryByCode.get();
        assertEquals(code,country.getCode());
    }
}
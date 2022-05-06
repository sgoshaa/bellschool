package com.bell.bellschooll.repository;

import com.bell.bellschooll.model.Country;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
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
package com.bell.bellschooll.service;

import com.bell.bellschooll.dto.response.CountryDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItemInArray;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class CountryServiceTest {
    @Autowired
    CountryService countryService;

    @Test
    void getAllCountry() {
        List<CountryDto> allCountry = countryService.getAllCountry();
        assertNotNull(allCountry);
        List<String> stringList = allCountry.stream()
                .map(CountryDto::getCode)
                .collect(Collectors.toList());
        assertThat(stringList, hasItem("643"));
    }
}
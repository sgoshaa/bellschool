//package com.bell.bellschooll.repository;
//
//import com.bell.bellschooll.model.Country;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.test.context.TestPropertySource;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest(classes = CountryRepository.class)
//@EnableAutoConfiguration
//@ComponentScan("com.bell.bellschooll.model")
//@EnableJpaRepositories
//@TestPropertySource(locations = "classpath:application-test.properties")
//class CountryRepositoryTest {
//
//    @Autowired
//    CountryRepository countryRepository;
//
//    @Test
//    void getCountryByCode() {
//        Optional<Country> countryByCode = countryRepository.getCountryByCode("643");
//        Country country = countryByCode.get();
//        assertEquals("643",country.getCode());
//    }
//}
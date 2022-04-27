package com.bell.bellschooll.repository;

import com.bell.bellschooll.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * DAO для работы с Country
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
    /**
     * Метод для получения страны по полю код
     *
     * @param code Код страны
     * @return Country Объект, содержащий страну
     */
    Optional<Country> getCountryByCode(String code);
}

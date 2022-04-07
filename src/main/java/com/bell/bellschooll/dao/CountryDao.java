package com.bell.bellschooll.dao;

import com.bell.bellschooll.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DAO для работы с Country
 */
@Repository
public interface CountryDao extends JpaRepository<Country, Integer> {
    /**
     * Метод для получения страны по полю код
     *
     * @param code Код страны
     * @return Country Объект, содержащий страну
     */
    Country getCountryByCode(Integer code);
}

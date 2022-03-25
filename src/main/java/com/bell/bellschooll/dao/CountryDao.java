package com.bell.bellschooll.dao;

import com.bell.bellschooll.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryDao extends JpaRepository<Country,Integer> {
    Country getCountryByCode(Integer code);
}

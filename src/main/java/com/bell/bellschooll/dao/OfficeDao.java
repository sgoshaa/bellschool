package com.bell.bellschooll.dao;

import com.bell.bellschooll.dto.request.OfficeInListDto;
import com.bell.bellschooll.model.Office;
import com.bell.bellschooll.model.Organization;

import java.util.List;

/**
 * DAO для работы с Office
 */
public interface OfficeDao {
    /**
     * Метод для получения офиса по id
     *
     * @param id
     * @return
     */
    Office getOfficeById(Integer id);

    /**
     * Метод для сохранения офиса
     *
     * @param office
     */
    void addOffice(Office office);

    /**
     * Метод для получения списка офисов по фильтру
     *
     * @param office
     * @param organization
     * @return
     */
    List<Office> getListOffice(OfficeInListDto office, Organization organization);

    /**
     * Обновление офиса
     *
     * @param office
     */
    void updateOffice(Office office);
}

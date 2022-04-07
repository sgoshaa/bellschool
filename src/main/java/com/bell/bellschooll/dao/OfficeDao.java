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
     * @param id уникальный идентификатор офиса
     * @return Office
     */
    Office getOfficeById(Integer id);

    /**
     * Метод для сохранения офиса
     *
     * @param office Объект,содержащий параметры,для сохранения офиса
     */
    void addOffice(Office office);

    /**
     * Метод для получения списка офисов по фильтру
     *
     * @param officeInListDto список параметров по которому мы фильтруем офисы
     * @param organization организация ,у которой есть данный офис
     * @return List объектов типа Office
     */
    List<Office> getListOffice(OfficeInListDto officeInListDto, Organization organization);

    /**
     * Обновление офиса
     *
     * @param office Объект,содержащий параметры офиса
     */
    void updateOffice(Office office);
}

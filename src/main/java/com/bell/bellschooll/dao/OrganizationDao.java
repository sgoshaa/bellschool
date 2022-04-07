package com.bell.bellschooll.dao;

import com.bell.bellschooll.dto.request.OrganisationDtoRequest;
import com.bell.bellschooll.dto.request.OrganizationSaveInDto;
import com.bell.bellschooll.model.Organization;

import java.util.List;

/**
 * DAO для работы с Organization
 */
public interface OrganizationDao {
    /**
     * Получение организации по id
     *
     * @param id уникальный идентификатор организации
     * @return Organization Объект,содержащий организацию
     */
    Organization getOrganizationById(Integer id);

    /**
     * Получение списка организации по фильтру
     *
     * @param organisationDtoRequest Объект, содержащий параметры для фильтрации организации
     * @return List объектов типа Organization
     */
    List<Organization> getListOrganizationByName(OrganisationDtoRequest organisationDtoRequest);

    /**
     * Сохранение организации
     *
     * @param organization Объект, содержащий описание организации
     */
    void save(Organization organization);

    /**
     * Обновление организации
     *
     * @param organization Объект, содержащий описание организации
     */
    void update(Organization organization);

}

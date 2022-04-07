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
     * @param id
     * @return
     */
    Organization getOrganizationById(Integer id);

    /**
     * Получение списка организации по фильтру
     *
     * @param organisationDtoRequest
     * @return
     */
    List<Organization> getListOrganizationByName(OrganisationDtoRequest organisationDtoRequest);

    /**
     * Сохранение организации
     *
     * @param organization
     */
    void save(Organization organization);

    /**
     * Обновление организации
     *
     * @param organization
     */
    void update(Organization organization);

}

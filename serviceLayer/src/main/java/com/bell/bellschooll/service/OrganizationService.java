package com.bell.bellschooll.service;

import com.bell.bellschooll.dto.request.OrganisationDtoRequest;
import com.bell.bellschooll.dto.request.OrganizationSaveInDto;
import com.bell.bellschooll.dto.request.OrganizationUpdateInDto;
import com.bell.bellschooll.dto.response.OrganizationListOut;
import com.bell.bellschooll.dto.response.OrganizationOutDto;
import com.bell.bellschooll.dto.response.SuccessDto;
import com.bell.bellschooll.model.Organization;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Интерфейс сервиса для работы с Organization
 */
public interface OrganizationService {
    /**
     * Метод возвращает OrganizationOutDto по id организации
     *
     * @param id Уникальный идентификатор организации
     * @return ResponseEntity<OrganizationOutDto>
     */
    ResponseEntity<OrganizationOutDto> getOrganizationById(Integer id);

    /**
     * Метод возвращает список OrganizationListOut по OrganisationDtoRequest
     *
     * @param organisationDTO объект с параметрами для фильтрации организаций
     * @return ResponseEntity<List < OrganizationListOut>>
     */
    ResponseEntity<List<OrganizationListOut>> getOrganizationByOrganizationDtoRequest(OrganisationDtoRequest organisationDTO);

    /**
     * Метод для добавления организации организации в БД
     *
     * @param organizationSaveInDto Объект для сохранения новой организации
     * @return ResponseEntity<SuccessDto>
     */
    ResponseEntity<SuccessDto> addOrganization(OrganizationSaveInDto organizationSaveInDto);

    /**
     * Метод для обновления организации
     *
     * @param organizationUpdateInDto объект с новыми параметрами организации
     * @return ResponseEntity<SuccessDto>
     */
    ResponseEntity<SuccessDto> updateOrganization(OrganizationUpdateInDto organizationUpdateInDto);

    /**
     * Служебный метод для поиска организации по id
     *
     * @param id уникальный идентификатор организации
     * @return Organization
     */
    Organization getOrgById(Integer id);
}

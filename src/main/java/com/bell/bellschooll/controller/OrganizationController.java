package com.bell.bellschooll.controller;

import com.bell.bellschooll.dto.request.OrganizationSaveInDto;
import com.bell.bellschooll.dto.request.OrganizationUpdateInDto;
import com.bell.bellschooll.dto.response.OrganizationListOut;
import com.bell.bellschooll.dto.response.OrganizationOutDto;
import com.bell.bellschooll.dto.response.SuccessDto;
import com.bell.bellschooll.service.OrganizationService;
import com.bell.bellschooll.dto.request.OrganisationDtoRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер для работы с Organization
 */
@RestController
@RequestMapping("api/organization/")
public class OrganizationController {

    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    /**
     * Получение ораганизации по фильтру
     *
     * @param organisationDTO Объект,содержащий параметры, для фильтрации организаций
     * @return List Список объектов OrganizationListOut
     */
    @PostMapping("list")
    public ResponseEntity<List<OrganizationListOut>> getListOrganization(@Valid @RequestBody OrganisationDtoRequest organisationDTO) {
        return organizationService.getOrganizationByName(organisationDTO);
    }

    /**
     * Получение организации по id
     *
     * @param id Уникальный идентификатор организации
     * @return OrganizationOutDto
     */
    @GetMapping("{id}")
    public ResponseEntity<OrganizationOutDto> getOrganizationById(@PathVariable Integer id) {
        return organizationService.getOrganizationById(id);
    }

    /**
     * Сохранение новой организации
     *
     * @param organizationSaveInDto Объект, содержащий  параметры новой организации
     * @return SuccessDto
     */
    @PostMapping("save")
    public ResponseEntity<SuccessDto> addOrganization(@Valid @RequestBody OrganizationSaveInDto organizationSaveInDto) {
        return organizationService.addOrganization(organizationSaveInDto);
    }

    /**
     * Обновление организации
     *
     * @param organizationUpdateInDto Объект, содержащий  параметры, для обновления организации
     * @return SuccessDto
     */
    @PostMapping("update")
    public ResponseEntity<SuccessDto> updateOrganization(@Valid @RequestBody OrganizationUpdateInDto organizationUpdateInDto) {
        return organizationService.updateOrganization(organizationUpdateInDto);
    }
}

package com.bell.bellschooll.service;

import com.bell.bellschooll.repository.OrganizationRepository;
import com.bell.bellschooll.repository.specification.OrganizationSpecification;
import com.bell.bellschooll.dto.request.OrganisationDtoRequest;
import com.bell.bellschooll.dto.request.OrganizationSaveInDto;
import com.bell.bellschooll.dto.request.OrganizationUpdateInDto;
import com.bell.bellschooll.dto.response.OrganizationListOut;
import com.bell.bellschooll.dto.response.SuccessDto;
import com.bell.bellschooll.exception.ErrorException;
import com.bell.bellschooll.dto.response.OrganizationOutDto;
import com.bell.bellschooll.mapper.OrganizationMapper;
import com.bell.bellschooll.model.Organization;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Сервис для Organization
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationMapper organizationMapper;
    private final OrganizationRepository organizationRepository;
    private final OrganizationSpecification organizationSpecification;

    public OrganizationServiceImpl(OrganizationMapper organizationMapper, OrganizationRepository organizationRepository, OrganizationSpecification organizationSpecification) {
        this.organizationMapper = organizationMapper;
        this.organizationRepository = organizationRepository;
        this.organizationSpecification = organizationSpecification;
    }

    /**
     * Метод для получения организации по id
     *
     * @param id Уникальный идентификатор организации
     * @return OrganizationOutDto
     */
    @Override
    public ResponseEntity<OrganizationOutDto> getOrganizationById(Integer id) {
        Organization organization = getOrgById(id);
        OrganizationOutDto organizationOutDto = organizationMapper.organizationToDto(organization);
        return new ResponseEntity<OrganizationOutDto>(organizationOutDto, HttpStatus.OK);
    }

    /**
     * Метод для получения организации по имени
     *
     * @param organisationDTO Объект,содержащий параметры, для фильтрации организаций
     * @return List Список объектов типа OrganizationListOut
     */
    @Override
    public ResponseEntity<List<OrganizationListOut>> getOrganizationByOrganizationDtoRequest(OrganisationDtoRequest organisationDTO) {
        List<Organization> organizationList = organizationRepository.findAll(organizationSpecification.getSpecification(organisationDTO));
        List<OrganizationListOut> organizations = new ArrayList<>();
        organizationList.forEach(organization -> {
            organizations.add(organizationMapper.organizationToListDto(organization));
        });
        return new ResponseEntity<>(organizations, HttpStatus.OK);
    }

    /**
     * Метод для сохранения новой организации
     *
     * @param organizationSaveInDto Объект, содержащий параметры для новой организации
     * @return SuccessDto
     */
    @Override
    @Transactional
    public ResponseEntity<SuccessDto> addOrganization(OrganizationSaveInDto organizationSaveInDto) {
        Organization organization = organizationMapper.organizationInToDomain(organizationSaveInDto);
        organizationRepository.save(organization);
        return new ResponseEntity<>(new SuccessDto(), HttpStatus.OK);
    }

    /**
     * Метод для обновления организации
     *
     * @param organizationUpdateInDto Объект, содержащий параметры для обновления организаци
     * @return SuccessDto
     */
    @Override
    @Transactional
    public ResponseEntity<SuccessDto> updateOrganization(OrganizationUpdateInDto organizationUpdateInDto) {
        Organization organization = getOrgById(organizationUpdateInDto.getId());
        organization = organizationMapper.organizationInToDomainUpdate(organizationUpdateInDto, organization);
        organizationRepository.save(organization);
        return new ResponseEntity<>(new SuccessDto(), HttpStatus.OK);
    }

    /**
     * Служебный метод для получения организации по id
     *
     * @param id Уникальный идентификатор организации
     * @return Organization
     */
    public Organization getOrgById(Integer id) {
        Optional<Organization> organization = organizationRepository.findById(id);
        if (organization.isEmpty()) {
            throw new ErrorException("Не найдена организация с id = " + id);
        }
        return organization.get();
    }
}

package com.bell.bellschooll.service;

import com.bell.bellschooll.dao.OrganizationDaoImpl;
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


@Service
public class OrganizationService {
    private final OrganizationDaoImpl organizationDao;
    private final OrganizationMapper organizationMapper;

    public OrganizationService(OrganizationDaoImpl organizationDao, OrganizationMapper organizationMapper) {
        this.organizationDao = organizationDao;
        this.organizationMapper = organizationMapper;
    }

    public ResponseEntity<OrganizationOutDto> getOrganizationById(Integer id) {
        Organization organization = getOrgById(id);
        OrganizationOutDto organizationOutDto = organizationMapper.organizationToDto(organization);
        return new ResponseEntity<OrganizationOutDto>(organizationOutDto, HttpStatus.OK);
    }

    public ResponseEntity<List<OrganizationListOut>> getOrganizationByName(OrganisationDtoRequest organisationDTO) {
        List<Organization> organizationList = organizationDao.getListOrganizationByName(organisationDTO);
        List<OrganizationListOut> organizations = new ArrayList<>();
        organizationList.forEach(organization -> {
            organizations.add(organizationMapper.organizationToListDto(organization));
        });
        return new ResponseEntity<>(organizations, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<SuccessDto> addOrganization(OrganizationSaveInDto organizationSaveInDto) {
        Organization organization = organizationMapper.organizationInToDomain(organizationSaveInDto);
        organizationDao.save(organization);
        return new ResponseEntity<>(new SuccessDto(), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<SuccessDto> updateOrganization(OrganizationUpdateInDto organizationUpdateInDto) {
        Organization organization = getOrgById(organizationUpdateInDto.getId());
        organization = organizationMapper.organizationInToDomainUpdate(organizationUpdateInDto, organization);
        organizationDao.update(organization);
        return new ResponseEntity<>(new SuccessDto(), HttpStatus.OK);
    }

    public Organization getOrgById(Integer id) {
        Organization organization = organizationDao.getOrganizationById(id);
        if (organization == null) {
            throw new ErrorException("Не найдена организация с id = "+id);
        }
        return organization;
    }
}

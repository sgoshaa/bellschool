package com.bell.bellschooll.service;

import com.bell.bellschooll.dao.OrganizationDaoImpl;
import com.bell.bellschooll.dto.request.OrganisationDtoRequest;
import com.bell.bellschooll.dto.response.OrganizationListOut;
import com.bell.bellschooll.exception.ErrorException;
import com.bell.bellschooll.dto.response.OrganizationOutDto;
import com.bell.bellschooll.mapper.OrganizationMapper;
import com.bell.bellschooll.model.Organization;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class OrganizationService {
    private final OrganizationDaoImpl organizationDao;
    private final OrganizationMapper organizationMapper = Mappers.getMapper(OrganizationMapper.class);

    public OrganizationService(OrganizationDaoImpl organizationDao) {
        this.organizationDao = organizationDao;
    }
    public ResponseEntity<OrganizationOutDto> getOrganizationById(Integer id){
        Organization organization = organizationDao.getOrganizationById(id);
        if (organization == null){
            throw new ErrorException("Организация не найдена!");
        }
        OrganizationOutDto organizationOutDto = organizationMapper.organizationToDto(organization);
        return new ResponseEntity<OrganizationOutDto>(organizationOutDto, HttpStatus.OK);
    }

    public ResponseEntity<List<OrganizationListOut>> getOrganizationByName(OrganisationDtoRequest organisationDTO) {
        List<Organization> organizationList = organizationDao.getListOrganizationByName(organisationDTO);
        List<OrganizationListOut> organizations = new ArrayList<>();
        organizationList.forEach(organization -> {
            organizations.add(organizationMapper.organizationToListDto(organization));
        });
        return new ResponseEntity<>(organizations,HttpStatus.OK);
    }
}

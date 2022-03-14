package com.bell.bellschooll.service;

import com.bell.bellschooll.dao.OrganizationRepository;
import com.bell.bellschooll.exception.ErrorException;
import com.bell.bellschooll.exception.ErrorResponseException;
import com.bell.bellschooll.dto.response.OrganizationOutDto;
import com.bell.bellschooll.mapper.OrganizationMapper;
import com.bell.bellschooll.model.Organization;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrganizationService {
    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper organisationMapper = Mappers.getMapper(OrganizationMapper.class);

    public OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public ResponseEntity<OrganizationOutDto> getOrganizationById(Integer id){

        Optional<Organization>organizationOptional = organizationRepository.findById(id);
        if (organizationOptional.isEmpty())
        {
            throw new ErrorException("Организация не найдена!");
        }
        Organization organization = organizationOptional.get();
        OrganizationOutDto organizationOutDto = organisationMapper.organizationToDto(organization);

        return new ResponseEntity<OrganizationOutDto>(organizationOutDto, HttpStatus.OK);
    }
}

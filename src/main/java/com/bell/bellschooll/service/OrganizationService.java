package com.bell.bellschooll.service;

import com.bell.bellschooll.dto.request.OrganisationDtoRequest;
import com.bell.bellschooll.dto.request.OrganizationSaveInDto;
import com.bell.bellschooll.dto.request.OrganizationUpdateInDto;
import com.bell.bellschooll.dto.response.OrganizationListOut;
import com.bell.bellschooll.dto.response.OrganizationOutDto;
import com.bell.bellschooll.dto.response.SuccessDto;
import com.bell.bellschooll.model.Organization;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrganizationService {

    ResponseEntity<OrganizationOutDto> getOrganizationById(Integer id);

    ResponseEntity<List<OrganizationListOut>> getOrganizationByName(OrganisationDtoRequest organisationDTO);

    ResponseEntity<SuccessDto> addOrganization(OrganizationSaveInDto organizationSaveInDto);

    ResponseEntity<SuccessDto> updateOrganization(OrganizationUpdateInDto organizationUpdateInDto);

    Organization getOrgById(Integer id);
}

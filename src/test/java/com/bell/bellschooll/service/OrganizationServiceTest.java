package com.bell.bellschooll.service;

import com.bell.bellschooll.Application;
import com.bell.bellschooll.dto.request.OrganisationDtoRequest;
import com.bell.bellschooll.dto.request.OrganizationSaveInDto;
import com.bell.bellschooll.dto.request.OrganizationUpdateInDto;
import com.bell.bellschooll.dto.response.OrganizationListOut;
import com.bell.bellschooll.dto.response.OrganizationOutDto;
import com.bell.bellschooll.dto.response.SuccessDto;
import com.bell.bellschooll.util.ConstantValue;
import com.bell.bellschooll.util.OrganizationRequestHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(classes = Application.class)
@TestPropertySource(locations = "classpath:application-test.properties")
class OrganizationServiceTest {

    @Autowired
    OrganizationService organizationService;

    @Test
    void addOrganization() {
        OrganizationSaveInDto organizationSaveInDto = OrganizationRequestHelper.createOrganizationSaveInDto();
        ResponseEntity<SuccessDto> successDtoResponseEntity = organizationService.addOrganization(organizationSaveInDto);
        assertEquals(ConstantValue.RESULT, successDtoResponseEntity.getBody().getResult());
    }

    @Test
    void getOrganizationById() {
        ResponseEntity<OrganizationOutDto> organizationById = organizationService.getOrganizationById(ConstantValue.ID);
        assertNotNull(organizationById);
        assertEquals(ConstantValue.ID, organizationById.getBody().getId());
    }

    @Test
    void getOrganizationByName() {
        OrganisationDtoRequest organisationDtoRequest = OrganizationRequestHelper.createOrganisationDtoRequest();
        ResponseEntity<List<OrganizationListOut>> organizationByName = organizationService.getOrganizationByName(organisationDtoRequest);
        assertTrue(organizationByName.getBody().size()>0);
    }

    @Test
    void updateOrganization() {
        OrganizationUpdateInDto organizationUpdateInDto = OrganizationRequestHelper.createOrganizationUpdateInDto();
        ResponseEntity<SuccessDto> successDtoResponseEntity
                = organizationService.updateOrganization(organizationUpdateInDto);
        assertEquals(ConstantValue.RESULT, successDtoResponseEntity.getBody().getResult());
    }
}
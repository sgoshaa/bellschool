package com.bell.bellschooll.service;

import com.bell.bellschooll.dao.OrganizationDao;
import com.bell.bellschooll.dto.request.OrganisationDtoRequest;
import com.bell.bellschooll.dto.request.OrganizationSaveInDto;
import com.bell.bellschooll.dto.request.OrganizationUpdateInDto;
import com.bell.bellschooll.dto.response.OrganizationListOut;
import com.bell.bellschooll.dto.response.OrganizationOutDto;
import com.bell.bellschooll.dto.response.SuccessDto;
import com.bell.bellschooll.exception.ErrorException;
import com.bell.bellschooll.mapper.OrganizationMapper;
import com.bell.bellschooll.util.ConstantValue;
import com.bell.bellschooll.util.OrganizationHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class OrganizationServiceImplTest {
    @MockBean
    OrganizationDao organizationDao;

    @Autowired
    OrganizationMapper organizationMapper;

    @Autowired
    private OrganizationService organizationService = new OrganizationServiceImpl(organizationDao,organizationMapper);


    @Test
    void addOrganization() {
        OrganizationSaveInDto organizationSaveInDto = OrganizationHelper.createOrganizationSaveInDto();
        when(organizationService.addOrganization(organizationSaveInDto)).thenReturn(new ResponseEntity<>(new SuccessDto(), HttpStatus.OK));
       // doNothing().when(organizationDao).save();
        ResponseEntity<SuccessDto> successDtoResponseEntity = organizationService.addOrganization(organizationSaveInDto);
        verify(organizationService).addOrganization(organizationSaveInDto);
        assertNotNull(successDtoResponseEntity);
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
        OrganisationDtoRequest organisationDtoRequest = OrganizationHelper.createOrganisationDtoRequest();
        ResponseEntity<List<OrganizationListOut>> organizationByName = organizationService.getOrganizationByName(organisationDtoRequest);
        assertTrue(organizationByName.getBody().size() > 0);
    }

    @Test
    void updateOrganization() {
        OrganizationUpdateInDto organizationUpdateInDto = OrganizationHelper.createOrganizationUpdateInDto();
        ResponseEntity<SuccessDto> successDtoResponseEntity
                = organizationService.updateOrganization(organizationUpdateInDto);
        assertEquals(ConstantValue.RESULT, successDtoResponseEntity.getBody().getResult());
    }

    @Test
    void getOrgById() {
        assertThrows(ErrorException.class, () -> organizationService.getOrgById(35));
    }
}
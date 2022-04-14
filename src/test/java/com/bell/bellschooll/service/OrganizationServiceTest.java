package com.bell.bellschooll.service;

import com.bell.bellschooll.dao.OrganizationDao;
import com.bell.bellschooll.dao.OrganizationDaoImpl;
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
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


//@SpringBootTest
//@TestPropertySource(locations = "classpath:application-test.properties")
@WebMvcTest(OrganizationService.class)
class OrganizationServiceTest {
    @MockBean
    OrganizationDaoImpl organizationDao;

    @Autowired
    OrganizationMapper organizationMapper;

    @Autowired
    OrganizationService organizationService = new OrganizationService(organizationDao, organizationMapper);


    @Test
    void addOrganization() {
        OrganizationSaveInDto organizationSaveInDto = OrganizationHelper.createOrganizationSaveInDto();
        when(organizationService.addOrganization(organizationSaveInDto)).thenReturn(new ResponseEntity<>(new SuccessDto(), HttpStatus.OK));
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
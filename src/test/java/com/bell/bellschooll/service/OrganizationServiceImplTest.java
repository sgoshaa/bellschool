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
import com.bell.bellschooll.model.Organization;
import com.bell.bellschooll.util.ConstantValue;
import com.bell.bellschooll.util.OrganizationHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
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
    private OrganizationService organizationService = new OrganizationServiceImpl(organizationDao, organizationMapper);


    @Test
    void addOrganization() {

        OrganizationSaveInDto organizationSaveInDto = OrganizationHelper.createOrganizationSaveInDto();
        ResponseEntity<SuccessDto> successDtoResponseEntity = organizationService.addOrganization(organizationSaveInDto);

        verify(organizationDao, times(1)).save(organizationMapper.organizationInToDomain(organizationSaveInDto));

        assertNotNull(successDtoResponseEntity);
        assertEquals(ConstantValue.RESULT, successDtoResponseEntity.getBody().getResult());
    }

    @Test
    void getOrganizationById() {
        int organizationId = ConstantValue.ID;
        Organization organization = OrganizationHelper.createOrganization();
        organization.setId(organizationId);

        when(organizationDao.getOrganizationById(organizationId)).thenReturn(organization);

        ResponseEntity<OrganizationOutDto> organizationById = organizationService.getOrganizationById(ConstantValue.ID);

        verify(organizationDao, times(1)).getOrganizationById(organizationId);

        assertNotNull(organizationById);
        assertEquals(ConstantValue.ID, organizationById.getBody().getId());
        assertEquals(HttpStatus.OK, organizationById.getStatusCode());
    }

    @Test
    void getOrganizationByOrganizationDtoRequest() {
        OrganisationDtoRequest organisationDtoRequest = OrganizationHelper.createOrganisationDtoRequest();
        Organization first = OrganizationHelper.createOrganization();
        first.setName("first");
        Organization second = OrganizationHelper.createOrganization();
        second.setName("second");

        List<Organization> organizationList = new ArrayList<>();
        organizationList.add(first);
        organizationList.add(second);

        when(organizationDao.getListOrganizationByOrganizationDtoRequest(organisationDtoRequest))
                .thenReturn(organizationList);

        ResponseEntity<List<OrganizationListOut>> organizationByName
                = organizationService.getOrganizationByOrganizationDtoRequest(organisationDtoRequest);

        verify(organizationDao).getListOrganizationByOrganizationDtoRequest(organisationDtoRequest);
        assertNotNull(organizationByName);
        assertEquals(organizationList.size(), organizationByName.getBody().size());

        assertTrue(organizationByName.getBody().size() > 0);

        assertThat(organizationByName.getBody()
                        .stream()
                        .map(OrganizationListOut::getName)
                        .collect(Collectors.toList())
                , hasItem("first"));
    }

    @Test
    void updateOrganization() {
        OrganizationUpdateInDto organizationUpdateInDto = OrganizationHelper.createOrganizationUpdateInDto();
        Organization currentOrganization = OrganizationHelper.createOrganization();
        currentOrganization.setId(organizationUpdateInDto.getId());

        when(organizationDao.getOrganizationById(currentOrganization.getId())).thenReturn(currentOrganization);

        ResponseEntity<SuccessDto> successDtoResponseEntity
                = organizationService.updateOrganization(organizationUpdateInDto);

        assertNotNull(successDtoResponseEntity);

        verify(organizationDao)
                .update(organizationMapper.organizationInToDomainUpdate(organizationUpdateInDto, currentOrganization));

        verify(organizationDao).getOrganizationById(currentOrganization.getId());


        assertEquals(ConstantValue.RESULT, successDtoResponseEntity.getBody().getResult());
    }

    @Test
    void getOrgById() {
        when(organizationDao.getOrganizationById(ConstantValue.ID)).thenReturn(null);
        assertThrows(ErrorException.class, () -> organizationService.getOrgById(ConstantValue.ID));
        verify(organizationDao).getOrganizationById(ConstantValue.ID);
    }
}
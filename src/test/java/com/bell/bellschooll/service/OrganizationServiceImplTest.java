package com.bell.bellschooll.service;

import com.bell.bellschooll.dto.request.OrganisationDtoRequest;
import com.bell.bellschooll.dto.request.OrganizationSaveInDto;
import com.bell.bellschooll.dto.request.OrganizationUpdateInDto;
import com.bell.bellschooll.dto.response.OrganizationListOut;
import com.bell.bellschooll.dto.response.OrganizationOutDto;
import com.bell.bellschooll.dto.response.SuccessDto;
import com.bell.bellschooll.exception.anyUserErrorException;
import com.bell.bellschooll.mapper.OrganizationMapper;
import com.bell.bellschooll.model.Organization;
import com.bell.bellschooll.repository.OrganizationRepository;
import com.bell.bellschooll.repository.specification.OrganizationSpecification;
import com.bell.bellschooll.util.ConstantValue;
import com.bell.bellschooll.util.OrganizationHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class OrganizationServiceImplTest {

    @MockBean
    OrganizationRepository organizationRepository;

    @Autowired
    OrganizationMapper organizationMapper;

    @Autowired
    OrganizationService organizationService;

    @MockBean
    OrganizationSpecification organizationSpecification;


    @Test
    @DisplayName("Метод проверяет работу метода сервиса addOrganization()")
    void addOrganization() {
        //Given
        OrganizationSaveInDto organizationSaveInDto = OrganizationHelper.createOrganizationSaveInDto();
        Organization entity = organizationMapper.organizationInToDomain(organizationSaveInDto);

        when(organizationRepository.save(entity)).thenReturn(entity);

        //When
        ResponseEntity<SuccessDto> successDtoResponseEntity = organizationService.addOrganization(organizationSaveInDto);

        //Then
        verify(organizationRepository).save(entity);
        assertNotNull(successDtoResponseEntity);
        assertEquals(ConstantValue.RESULT, successDtoResponseEntity.getBody().getResult());
        assertEquals(entity.getName(), organizationSaveInDto.getName());
        assertEquals(entity.getAddress(), organizationSaveInDto.getAddress());
        assertEquals(entity.getFullName(), organizationSaveInDto.getFullName());
        assertEquals(entity.getPhone(), organizationSaveInDto.getPhone());
        assertEquals(entity.getInn(), organizationSaveInDto.getInn());
        assertEquals(entity.getKpp(), organizationSaveInDto.getKpp());
        assertEquals(entity.getIsActive(), organizationSaveInDto.getIsActive());
    }

    @Test
    @DisplayName("Метод проверяет работу метода сервиса getOrganizationById по ID = 1 ")
    void getOrganizationById() {
        //Given
        int organizationId = ConstantValue.ID;
        Organization organization = OrganizationHelper.createOrganization();

        when(organizationRepository.findById(organizationId)).thenReturn(Optional.of(organization));

        //When
        ResponseEntity<OrganizationOutDto> organizationById = organizationService.getOrganizationById(ConstantValue.ID);

        //Then
        verify(organizationRepository).findById(organizationId);
        assertNotNull(organizationById);
        assertEquals(ConstantValue.ID, organizationById.getBody().getId());
        assertEquals(HttpStatus.OK, organizationById.getStatusCode());
    }

    @Test
    @DisplayName("Метод проверяет работу метода сервиса getOrganizationByOrganizationDtoRequest()")
    void getOrganizationByOrganizationDtoRequest() {
        //Given
        OrganisationDtoRequest organisationDtoRequest = OrganizationHelper.createOrganisationDtoRequest();
        Organization first = OrganizationHelper.createOrganization();
        first.setName("first");
        Organization second = OrganizationHelper.createOrganization();
        second.setName("second");

        List<Organization> organizationList = new ArrayList<>();
        organizationList.add(first);
        organizationList.add(second);

        when(organizationRepository.findAll(organizationSpecification.getSpecification(organisationDtoRequest)))
                .thenReturn(organizationList);

        //When
        ResponseEntity<List<OrganizationListOut>> organizationByName
                = organizationService.getOrganizationByOrganizationDtoRequest(organisationDtoRequest);

        //Then
        verify(organizationRepository).findAll(organizationSpecification.getSpecification(organisationDtoRequest));
        assertNotNull(organizationByName);
        assertEquals(organizationList.size(), organizationByName.getBody().size());

        assertTrue(organizationByName.getBody().size() > 0);
        assertThat(organizationByName.getBody()
                        .stream()
                        .map(OrganizationListOut::getName)
                        .collect(Collectors.toList())
                , hasItems("first", "second"));
    }

    @Test
    @DisplayName("метод проверяет метод сервиса updateOrganization() ")
    void updateOrganization() {
        //Given
        OrganizationUpdateInDto organizationUpdateInDto = OrganizationHelper.createOrganizationUpdateInDto();
        Organization currentOrganization = OrganizationHelper.createOrganization();
        currentOrganization.setId(organizationUpdateInDto.getId());

        Organization entity = organizationMapper.organizationInToDomainUpdate(organizationUpdateInDto, currentOrganization);
        when(organizationRepository.findById(organizationUpdateInDto.getId())).thenReturn(Optional.of(currentOrganization));
        when(organizationRepository.save(entity)).thenReturn(entity);

        //When
        ResponseEntity<SuccessDto> successDtoResponseEntity
                = organizationService.updateOrganization(organizationUpdateInDto);

        //Then
        assertNotNull(successDtoResponseEntity);
        verify(organizationRepository).save(entity);
        verify(organizationRepository).findById(currentOrganization.getId());
        assertEquals(ConstantValue.RESULT, successDtoResponseEntity.getBody().getResult());
    }

    @Test
    @DisplayName("Проверяет работу метода getOrgById(), результатом должно быть исключение")
    void getOrgById() {
        //When
        when(organizationRepository.findById(ConstantValue.ID)).thenReturn(Optional.empty());

        //Then
        assertThrows(anyUserErrorException.class, () -> organizationService.getOrgById(ConstantValue.ID));
        verify(organizationRepository).findById(ConstantValue.ID);
    }
}
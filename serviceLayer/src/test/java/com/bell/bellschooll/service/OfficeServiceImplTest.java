package com.bell.bellschooll.service;

import com.bell.bellschooll.dto.request.OfficeInListDto;
import com.bell.bellschooll.dto.request.OfficeInSaveDto;
import com.bell.bellschooll.dto.request.OfficeInUpdateDto;
import com.bell.bellschooll.dto.response.OfficeListOutDto;
import com.bell.bellschooll.dto.response.OfficeOutDto;
import com.bell.bellschooll.dto.response.SuccessDto;
import com.bell.bellschooll.exception.anyUserErrorException;
import com.bell.bellschooll.mapper.OfficeMapper;
import com.bell.bellschooll.model.Office;
import com.bell.bellschooll.model.Organization;
import com.bell.bellschooll.repository.OfficeRepository;
import com.bell.bellschooll.specification.OfficeSpecification;
import com.bell.bellschooll.util.ConstantValue;
import com.bell.bellschooll.util.OfficeHelper;
import com.bell.bellschooll.util.OrganizationHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class OfficeServiceImplTest {

    @MockBean
    OfficeRepository officeRepository;

    @Autowired
    OfficeMapper officeMapper;

    @Autowired
    OfficeService officeService;

    @MockBean
    OrganizationService organizationService;

    @MockBean
    OfficeSpecification officeSpecification;

    @Test
    @DisplayName("Метод проверяет работу метода сервиса getOfficeById()")
    void getOfficeById() {
        //Given
        Office office = OfficeHelper.createOffice(OrganizationHelper.createOrganization());
        office.setId(ConstantValue.ID);

        when(officeRepository.findById(ConstantValue.ID)).thenReturn(Optional.of(office));

        //When
        ResponseEntity<OfficeOutDto> officeById = officeService.getOfficeById(ConstantValue.ID);

        //Then
        verify(officeRepository).findById(ConstantValue.ID);
        assertNotNull(officeById);
        assertEquals(ConstantValue.ID, officeById.getBody().getId());
        assertEquals(office.getName(), officeById.getBody().getName());
        assertEquals(office.getPhone(), officeById.getBody().getPhone());
        assertEquals(office.getAddress(), officeById.getBody().getAddress());
        assertEquals(office.getIsActive(), officeById.getBody().getIsActive());
    }

    @Test
    @Transactional
    @DisplayName("Метод проверяет работу метода сервиса addOffice()")
    void addOffice() {
        //Given
        Organization organization = OrganizationHelper.createOrganization();
        organization.setId(ConstantValue.ID);
        organization.setVersion(1);
        OfficeInSaveDto officeInSaveDto = OfficeHelper.createOfficeInSaveDto();
        Office entity = officeMapper.dtoToDomain(officeInSaveDto, organization);

        when(organizationService.getOrgById(organization.getId())).thenReturn(organization);
        when(officeRepository.save(entity)).thenReturn(entity);

        //When
        ResponseEntity<SuccessDto> successDtoResponseEntity = officeService.addOffice(officeInSaveDto);

        //Then
        verify(organizationService).getOrgById(organization.getId());
        verify(officeRepository).save(entity);
        assertNotNull(successDtoResponseEntity);
        assertEquals(ConstantValue.RESULT, successDtoResponseEntity.getBody().getResult());
    }

    @Test
    @DisplayName("Метод проверяет работы метода сервиса listOffice() ")
    void listOffice() {
        //Given
        Organization organization = OrganizationHelper.createOrganization();
        organization.setId(ConstantValue.ID);

        when(organizationService.getOrgById(organization.getId())).thenReturn(organization);

        Office first = OfficeHelper.createOffice(organization);
        first.setName("first");
        Office second = OfficeHelper.createOffice(organization);
        second.setName("second");
        List<Office> officeList = List.of(first, second);
        OfficeInListDto officeInListDto = OfficeHelper.createOfficeInListDto();

        when(officeRepository.findAll(officeSpecification
                .getSpecification(officeInListDto, organization))).thenReturn(officeList);

        //When
        ResponseEntity<List<OfficeListOutDto>> listResponseEntity = officeService.getListOffice(officeInListDto);

        //Then
        verify(organizationService).getOrgById(organization.getId());
        verify(officeRepository).findAll(officeSpecification.getSpecification(officeInListDto, organization));

        assertNotNull(listResponseEntity);
        assertEquals(officeList.size(), Objects.requireNonNull(listResponseEntity.getBody()).size());
        assertThat(listResponseEntity.getBody().stream()
                        .map(OfficeListOutDto::getName)
                        .collect(Collectors.toList())
                , hasItems("first", "second"));
    }

    @Test
    @DisplayName("Метод проверяет рабоату метода сервиса updateOffice() ")
    void updateOffice() {
        //Given
        OfficeInUpdateDto officeInUpdateDto = OfficeHelper.createOfficeInUpdateDto();
        Office office = OfficeHelper.createOffice(OrganizationHelper.createOrganization());
        office.setId(ConstantValue.ID);
        Office entity = officeMapper.updateOfficeDtoToDomain(officeInUpdateDto, office);

        when(officeRepository.findById(office.getId())).thenReturn(Optional.of(office));
        when(officeRepository.save(entity)).thenReturn(entity);

        //Then
        ResponseEntity<SuccessDto> successDtoResponseEntity = officeService.updateOffice(officeInUpdateDto);

        //When
        verify(officeRepository).findById(office.getId());
        verify(officeRepository).save(entity);
        assertNotNull(successDtoResponseEntity);
        assertEquals(ConstantValue.RESULT, successDtoResponseEntity.getBody().getResult());
    }

    @Test
    @DisplayName("Метод проверяет работу метода сервиса getOffice() и выбрасывает исключение")
    void getOffice() {
        //When
        when(officeRepository.findById(ConstantValue.ID)).thenReturn(Optional.empty());

        //then
        assertThrows(anyUserErrorException.class, () -> officeService.getOffice(ConstantValue.ID));
        verify(officeRepository).findById(ConstantValue.ID);
    }

    @Test
    @DisplayName("Метод проверяет работу сервиса listOffice ,который возвращает пустое значение ")
    void listOfficeEmptyList() {
        //Given
        OfficeInListDto officeInListDto = OfficeHelper.createOfficeInListDto();
        Organization organization = OrganizationHelper.createOrganization();

        when(officeRepository.findAll(officeSpecification
                .getSpecification(officeInListDto, organization))).thenReturn(Collections.emptyList());
        when(organizationService.getOrgById(officeInListDto.getOrgId())).thenReturn(organization);

        //When
        assertThrows(anyUserErrorException.class, () -> officeService.getListOffice(officeInListDto));

        //Then
        verify(officeRepository).findAll(officeSpecification.getSpecification(officeInListDto, organization));
        verify(organizationService).getOrgById(officeInListDto.getOrgId());
    }
}
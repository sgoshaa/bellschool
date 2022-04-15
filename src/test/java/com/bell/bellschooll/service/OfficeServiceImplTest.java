package com.bell.bellschooll.service;

import com.bell.bellschooll.dao.OfficeDao;
import com.bell.bellschooll.dto.request.OfficeInListDto;
import com.bell.bellschooll.dto.request.OfficeInSaveDto;
import com.bell.bellschooll.dto.request.OfficeInUpdateDto;
import com.bell.bellschooll.dto.response.OfficeListOutDto;
import com.bell.bellschooll.dto.response.OfficeOutDto;
import com.bell.bellschooll.dto.response.SuccessDto;
import com.bell.bellschooll.exception.ErrorException;
import com.bell.bellschooll.mapper.OfficeMapper;
import com.bell.bellschooll.model.Office;
import com.bell.bellschooll.model.Organization;
import com.bell.bellschooll.util.ConstantValue;
import com.bell.bellschooll.util.OfficeHelper;
import com.bell.bellschooll.util.OrganizationHelper;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class OfficeServiceImplTest {

    @MockBean
    OfficeDao officeDao;

    @Autowired
    OfficeMapper officeMapper;

    @Autowired
    OfficeService officeService;

    @MockBean
    OrganizationService organizationService;

    @Test
    void getOfficeById() {
        Office newOffice = OfficeHelper.createOffice(OrganizationHelper.createOrganization());
        newOffice.setId(ConstantValue.ID);

        when(officeDao.getOfficeById(ConstantValue.ID)).thenReturn(newOffice);
        ResponseEntity<OfficeOutDto> office = officeService.getOfficeById(ConstantValue.ID);
        verify(officeDao).getOfficeById(ConstantValue.ID);

        assertNotNull(office);
        assertEquals(ConstantValue.ID, office.getBody().getId());
        assertEquals(newOffice.getName(), office.getBody().getName());
    }

    @Test
    @Transactional
    void addOffice() {
        Organization organization = OrganizationHelper.createOrganization();
        organization.setId(ConstantValue.ID);
        organization.setVersion(1);

        when(organizationService.getOrgById(organization.getId())).thenReturn(organization);

        OfficeInSaveDto officeInSaveDto = OfficeHelper.createOfficeInSaveDto();
        ResponseEntity<SuccessDto> successDtoResponseEntity = officeService.addOffice(officeInSaveDto);

        verify(officeDao).addOffice(officeMapper.dtoToDomain(officeInSaveDto, organization));

        assertNotNull(successDtoResponseEntity);
        assertEquals(ConstantValue.RESULT, successDtoResponseEntity.getBody().getResult());
    }

    @Test
    void listOffice() {

        Organization organization = OrganizationHelper.createOrganization();
        organization.setId(ConstantValue.ID);

        when(organizationService.getOrgById(organization.getId())).thenReturn(organization);

        Office first = OfficeHelper.createOffice(organization);
        first.setName("first");
        Office second = OfficeHelper.createOffice(organization);
        second.setName("second");
        List<Office> officeList = List.of(first, second);
        OfficeInListDto officeInListDto = OfficeHelper.createOfficeInListDto();

        when(officeDao.getListOffice(officeInListDto, organization)).thenReturn(officeList);

        ResponseEntity<List<OfficeListOutDto>> listResponseEntity = officeService.getListOffice(officeInListDto);

        verify(officeDao).getListOffice(officeInListDto, organization);

        assertNotNull(listResponseEntity);
        assertEquals(officeList.size(), Objects.requireNonNull(listResponseEntity.getBody()).size());
        assertThat(listResponseEntity.getBody().stream()
                        .map(OfficeListOutDto::getName)
                        .collect(Collectors.toList())
                , hasItems("first", "second"));
    }

    @Test
    void updateOffice() {
        OfficeInUpdateDto officeInUpdateDto = OfficeHelper.createOfficeInUpdateDto();
        Office office = OfficeHelper.createOffice(OrganizationHelper.createOrganization());
        office.setId(ConstantValue.ID);

        when(officeDao.getOfficeById(office.getId())).thenReturn(office);
        ResponseEntity<SuccessDto> successDtoResponseEntity = officeService.updateOffice(officeInUpdateDto);

        verify(officeDao).updateOffice(officeMapper.updateOfficeDtoToDomain(officeInUpdateDto, office));
        assertNotNull(successDtoResponseEntity);
        assertEquals(ConstantValue.RESULT, successDtoResponseEntity.getBody().getResult());
    }

    @Test
    void getOffice() {
        when(officeDao.getOfficeById(ConstantValue.ID)).thenReturn(null);

        assertThrows(ErrorException.class, () -> officeService.getOffice(ConstantValue.ID));
        verify(officeDao).getOfficeById(ConstantValue.ID);
    }

    @Test
    void listOfficeEmptyList() {
        OfficeInListDto officeInListDto = OfficeHelper.createOfficeInListDto();
        officeInListDto.setOrgId(1);
        Organization organization = OrganizationHelper.createOrganization();
        organization.setId(1);

        when(officeDao.getListOffice(officeInListDto, organization)).thenReturn(Collections.emptyList());
        when(organizationService.getOrgById(officeInListDto.getOrgId())).thenReturn(organization);

        assertThrows(ErrorException.class, () -> officeService.getListOffice(officeInListDto));
        verify(officeDao).getListOffice(officeInListDto, organization);
        verify(organizationService).getOrgById(officeInListDto.getOrgId());
    }
}
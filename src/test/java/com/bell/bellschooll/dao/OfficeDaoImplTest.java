package com.bell.bellschooll.dao;

import com.bell.bellschooll.dto.request.OfficeInListDto;
import com.bell.bellschooll.model.Office;
import com.bell.bellschooll.model.Organization;
import com.bell.bellschooll.util.ConstantValue;
import com.bell.bellschooll.util.OfficeHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
class OfficeDaoImplTest {

    @Autowired
    OfficeDaoImpl officeDao;
    @Autowired
    OrganizationDao organizationDao;

    @Test
    void getOfficeById() {
        Office officeById = officeDao.getOfficeById(1);

        assertNotNull(officeById);
        assertEquals(1,officeById.getId());
    }

    @Test
    void addOffice() {
        Office office = OfficeHelper.createOffice(organizationDao.getOrganizationById(ConstantValue.ID));
        officeDao.addOffice(office);

        Office officeById = officeDao.getOfficeById(office.getId());

        assertNotNull(officeById);
        assertEquals(office.getId(),officeById.getId());
        assertEquals(office.getName(),officeById.getName());
        assertEquals(office.getAddress(),officeById.getAddress());
        assertEquals(office.getIsActive(),officeById.getIsActive());
        assertEquals(office.getOrganization(),officeById.getOrganization());
        assertEquals(office.getPhone(),officeById.getPhone());
    }

    @Test
    void getListOffice() {
        OfficeInListDto officeInListDto = OfficeHelper.createOfficeInListDto();
        List<Office> listOffice = officeDao.getListOffice(officeInListDto, organizationDao.getOrganizationById(ConstantValue.ID));

        assertFalse(listOffice.isEmpty());
    }

    @Test
    void getListOfficeNameAndPhone() {
        OfficeInListDto officeInListDto = OfficeHelper.createOfficeInListDto();
        officeInListDto.setName("офис Bell1");
        officeInListDto.setPhone("12321321321");
        List<Office> listOffice = officeDao.getListOffice(officeInListDto, organizationDao.getOrganizationById(ConstantValue.ID));

        assertFalse(listOffice.isEmpty());
        assertThat(listOffice.stream().map(Office::getName).collect(Collectors.toList()), hasItem(officeInListDto.getName()));
    }

    @Test
    void getListOfficeNameAndPhoneAndIsActive() {
        OfficeInListDto officeInListDto = OfficeHelper.createOfficeInListDto();
        officeInListDto.setName("офис Bell1");
        officeInListDto.setPhone("12321321321");
        officeInListDto.setIsActive(true);
        List<Office> listOffice = officeDao.getListOffice(officeInListDto, organizationDao.getOrganizationById(ConstantValue.ID));

        assertFalse(listOffice.isEmpty());
        assertThat(listOffice.stream()
                .findFirst()
                .get(),hasProperty("name",equalTo(officeInListDto.getName())));
    }

    @Test
    void updateOffice() {
        Office officeById = officeDao.getOfficeById(ConstantValue.ID);
        officeById.setName("UpdateName");
        officeDao.updateOffice(officeById);

        Office updateOffice = officeDao.getOfficeById(officeById.getId());

        assertNotNull(updateOffice);
        assertEquals(officeById.getName(),updateOffice.getName());
    }
}
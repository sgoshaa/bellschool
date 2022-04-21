package com.bell.bellschooll.repository;

import com.bell.bellschooll.dao.OrganizationDao;
import com.bell.bellschooll.dto.request.OfficeInListDto;
import com.bell.bellschooll.model.Office;
import com.bell.bellschooll.repository.OfficeRepository;
import com.bell.bellschooll.repository.specification.OfficeSpecification;
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

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
class OfficeRepositoryTest {

    public static final String BELL_1 = "офис Bell1";
    public static final String PHONE = "12321321321";
    @Autowired
    OfficeRepository officeRepository;

    @Autowired
    OrganizationDao organizationDao;

    @Autowired
    OfficeSpecification officeSpecification;

    @Test
    void getById() {
        Office officeById = officeRepository.getById(ConstantValue.ID);
        assertNotNull(officeById);
        assertEquals(ConstantValue.ID, officeById.getId());
    }

    @Test
    void saveOffice() {
        //Given
        Office office = OfficeHelper.createOffice(organizationDao.getOrganizationById(ConstantValue.ID));
        Office savedOffice = officeRepository.save(office);

        //When
        Office officeById = officeRepository.getById(savedOffice.getId());

        //Then
        assertNotNull(officeById);
        assertEquals(office.getId(), officeById.getId());
        assertEquals(office.getName(), officeById.getName());
        assertEquals(office.getAddress(), officeById.getAddress());
        assertEquals(office.getIsActive(), officeById.getIsActive());
        assertEquals(office.getOrganization(), officeById.getOrganization());
        assertEquals(office.getPhone(), officeById.getPhone());
    }

    @Test
    void getListOffice() {
        //Given
        OfficeInListDto officeInListDto = OfficeHelper.createOfficeInListDto();

        //When
        List<Office> listOffice = officeRepository.findAll(officeSpecification.getSpecification(officeInListDto
                , organizationDao.getOrganizationById(ConstantValue.ID)));

        //Then
        assertFalse(listOffice.isEmpty());
        assertThat(listOffice.stream()
                        .map(Office::getName)
                        .collect(Collectors.toList())
                , hasItem(BELL_1));
    }

    @Test
    void getListOfficeNameAndPhone() {
        //Given
        OfficeInListDto officeInListDto = OfficeHelper.createOfficeInListDto();
        officeInListDto.setName(BELL_1);
        officeInListDto.setPhone(PHONE);

        //When
        List<Office> listOffice = officeRepository.findAll(officeSpecification.getSpecification(officeInListDto
                , organizationDao.getOrganizationById(ConstantValue.ID)));

        //Then
        assertFalse(listOffice.isEmpty());
        assertThat(listOffice.stream().map(Office::getName).collect(Collectors.toList()), hasItem(officeInListDto.getName()));
    }

    @Test
    void getListOfficeNameAndPhoneAndIsActive() {
        //Given
        OfficeInListDto officeInListDto = OfficeHelper.createOfficeInListDto();
        officeInListDto.setName(BELL_1);
        officeInListDto.setPhone(PHONE);
        officeInListDto.setIsActive(true);

        //When
        List<Office> listOffice = officeRepository.findAll(officeSpecification.getSpecification(officeInListDto
                , organizationDao.getOrganizationById(ConstantValue.ID)));

        //Then
        assertFalse(listOffice.isEmpty());
        assertThat(listOffice.stream()
                .findFirst()
                .get(), hasProperty("name", equalTo(officeInListDto.getName())));
    }

    @Test
    void updateOffice() {
        //Given
        Office officeById = officeRepository.getById(ConstantValue.ID);
        officeById.setName("UpdateName");

        //When
        Office updatedOffice = officeRepository.save(officeById);

        //Then
        Office actualOffice = officeRepository.getById(updatedOffice.getId());

        assertNotNull(actualOffice);
        assertEquals(officeById.getName(), actualOffice.getName());
    }
}
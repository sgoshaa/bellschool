package com.bell.bellschooll.mapper;

import com.bell.bellschooll.dto.request.OfficeInSaveDto;
import com.bell.bellschooll.dto.request.OfficeInUpdateDto;
import com.bell.bellschooll.dto.response.OfficeListOutDto;
import com.bell.bellschooll.dto.response.OfficeOutDto;
import com.bell.bellschooll.model.Office;
import com.bell.bellschooll.model.Organization;
import com.bell.bellschooll.util.OfficeHelper;
import com.bell.bellschooll.util.OrganizationHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {OfficeMapper.class})
@ContextConfiguration(classes = MapperTestConfig.class )
class OfficeMapperTest {

    @Autowired
    OfficeMapper officeMapper;

    @Test
    void officeToDto() {
        //Given
        Office office = OfficeHelper.createOffice();
        OfficeOutDto officeOutDto = OfficeHelper.createOfficeOutDto();

        //When
        OfficeOutDto officeOutDtoActual = officeMapper.officeToDto(office);

        //Then
        assertEquals(officeOutDto, officeOutDtoActual);
        assertEquals(officeOutDto.getName(), officeOutDtoActual.getName());
        assertEquals(officeOutDto.getAddress(), officeOutDtoActual.getAddress());
        assertEquals(officeOutDto.getPhone(), officeOutDtoActual.getPhone());
        assertEquals(officeOutDto.getIsActive(), officeOutDtoActual.getIsActive());
    }

    @Test
    void dtoToDomain() {
        //Given
        OfficeInSaveDto officeInSaveDto = OfficeHelper.createOfficeInSaveDto();
        Office office = OfficeHelper.createOffice();
        Organization organization = OrganizationHelper.createOrganization();
        office.setOrganization(organization);

        //When
        Office officeActual = officeMapper.dtoToDomain(officeInSaveDto, organization);

        //Then
        assertEquals(office, officeActual);
        assertEquals(office.getOrganization(), officeActual.getOrganization());
        assertEquals(office.getIsActive(), officeActual.getIsActive());
        assertEquals(office.getName(), officeActual.getName());
        assertEquals(office.getAddress(), officeActual.getAddress());
        assertEquals(office.getPhone(), officeActual.getPhone());
    }

    @Test
    void officeToListDto() {
        //Given
        Office office = OfficeHelper.createOffice();
        OfficeListOutDto simpleOfficeForListOutDto = OfficeHelper.createSimpleOfficeForListOutDto();

        //When
        OfficeListOutDto officeListOutDto = officeMapper.officeToListDto(office);

        //Then
        assertEquals(simpleOfficeForListOutDto, officeListOutDto);
    }

    @Test
    void toListDto() {
        //Given
        List<OfficeListOutDto> officeListOutDto = OfficeHelper.createOfficeListOutDto();
        Office office = OfficeHelper.createOffice();
        ArrayList<Office> officeList = new ArrayList<>();
        officeList.add(office);

        //When
        List<OfficeListOutDto> officeListOutDtoActual = officeMapper.toListDto(officeList);

        //Then
        assertEquals(officeListOutDto.size(), officeListOutDtoActual.size());
        assertEquals(officeListOutDto, officeListOutDtoActual);
    }

    @Test
    void updateOfficeDtoToDomain() {
        //Given
        OfficeInUpdateDto officeInUpdateDto = OfficeHelper.createOfficeInUpdateDto();
        Office office = OfficeHelper.createOffice();
        office.setId(officeInUpdateDto.getId());
        office.setName(officeInUpdateDto.getName());
        office.setPhone(officeInUpdateDto.getPhone());
        office.setAddress(officeInUpdateDto.getAddress());
        office.setIsActive(officeInUpdateDto.getIsActive());

        //When
        Office officeActual = officeMapper.updateOfficeDtoToDomain(officeInUpdateDto, office);

        //Then
        assertEquals(office, officeActual);
    }
}
package com.bell.bellschooll.mapper;

import com.bell.bellschooll.dto.request.OrganizationSaveInDto;
import com.bell.bellschooll.dto.request.OrganizationUpdateInDto;
import com.bell.bellschooll.dto.response.OrganizationListOut;
import com.bell.bellschooll.dto.response.OrganizationOutDto;
import com.bell.bellschooll.model.Organization;
import com.bell.bellschooll.util.ConstantValue;
import com.bell.bellschooll.util.OrganizationHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {OrganizationMapper.class})
@ContextConfiguration(classes = MapperTestConfig.class )
class OrganizationMapperTest {

    @Autowired
    OrganizationMapper organizationMapper;

    @Test
    void organizationToDto() {
        //Given
        Organization organization = OrganizationHelper.createOrganization();
        OrganizationOutDto organizationOutDto = OrganizationHelper.createOrganizationOutDto();

        //When
        OrganizationOutDto organizationOutDtoActual = organizationMapper.organizationToDto(organization);

        //Then
        assertEquals(organizationOutDto, organizationOutDtoActual);
    }

    @Test
    void organizationToListDto() {
        //Given
        Organization organization = OrganizationHelper.createOrganization();
        OrganizationListOut organizationListOutExpected = OrganizationHelper.createOrganizationListOutDto();

        //When
        OrganizationListOut organizationListActual = organizationMapper.organizationToListDto(organization);

        //Then
        assertEquals(organizationListOutExpected, organizationListActual);
    }

    @Test
    void toListDto() {
        //Given
        ArrayList<Organization> organizationArrayList = new ArrayList<>();
        organizationArrayList.add(OrganizationHelper.createOrganization());

        OrganizationListOut organizationListOutDto = OrganizationHelper.createOrganizationListOutDto();
        ArrayList<OrganizationListOut> organizationListOutExpected = new ArrayList<>();
        organizationListOutExpected.add(organizationListOutDto);

        //When
        List<OrganizationListOut> organizationListActual = organizationMapper.toListDto(organizationArrayList);

        //Then
        assertEquals(organizationListOutExpected, organizationListActual);
    }

    @Test
    void organizationInToDomain() {
        //Given
        OrganizationSaveInDto organizationSaveInDto = OrganizationHelper.createOrganizationSaveInDto();
        Organization organization = OrganizationHelper.createOrganization();
        organization.setId(ConstantValue.ID);
        organization.setName(organizationSaveInDto.getName());
        organization.setAddress(organizationSaveInDto.getAddress());
        organization.setPhone(organizationSaveInDto.getPhone());
        organization.setFullName(organizationSaveInDto.getFullName());
        organization.setInn(organizationSaveInDto.getInn());
        organization.setKpp(organizationSaveInDto.getKpp());
        organization.setIsActive(organizationSaveInDto.getIsActive());

        //When
        Organization organizationActual = organizationMapper.organizationInToDomain(organizationSaveInDto);
        //todo удалить когда найду решение как игнорить некоторые поля
        organizationActual.setId(ConstantValue.ID);

        //Then
        assertEquals(organization, organizationActual);
    }

    @Test
    void organizationInToDomainUpdate() {
        //Given
        OrganizationUpdateInDto organizationUpdateInDto = OrganizationHelper.createOrganizationUpdateInDto();
        Organization organization = new Organization();
        organization.setId(organizationUpdateInDto.getId());
        organization.setInn(organizationUpdateInDto.getInn());
        organization.setKpp(organizationUpdateInDto.getKpp());
        organization.setAddress(organizationUpdateInDto.getAddress());
        organization.setPhone(organizationUpdateInDto.getPhone());
        organization.setName(organizationUpdateInDto.getName());
        organization.setFullName(organizationUpdateInDto.getFullName());
        organization.setIsActive(organizationUpdateInDto.getIsActive());

        //When
        Organization organizationActual = organizationMapper.organizationInToDomainUpdate(organizationUpdateInDto, organization);

        //Then
        assertEquals(organization, organizationActual);
    }
}
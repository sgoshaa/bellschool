package com.bell.bellschooll.util;

import com.bell.bellschooll.dto.request.OrganisationDtoRequest;
import com.bell.bellschooll.dto.request.OrganizationSaveInDto;
import com.bell.bellschooll.dto.request.OrganizationUpdateInDto;
import com.bell.bellschooll.dto.response.OrganizationListOut;
import com.bell.bellschooll.dto.response.OrganizationOutDto;
import com.bell.bellschooll.model.Organization;

/**
 * Класс для помощи при тестировании сервиса организаций
 */
public class OrganizationHelper {
    public static final String FULL_NAME = "full name";
    public static final String ADDRESS = "address";
    public static final String PHONE = "2-57-05";
    public static final String NAME = "name";
    public static final int KPP = 4567891;
    private static final String TEST_ORG_ADDRESS = "Test address";
    public static final int INN = 456456789;
    public static final String NEW_ORGANIZATION = "new organization";
    public static final String FN_ORGANIZATION = "FN organization";

    public static OrganizationSaveInDto createOrganizationSaveInDto() {
        OrganizationSaveInDto organizationSaveInDto = new OrganizationSaveInDto();
        organizationSaveInDto.setName("сбербанк");
        organizationSaveInDto.setFullName("ОАО Сбербанк");
        organizationSaveInDto.setInn(456789456);
        organizationSaveInDto.setKpp(54564564);
        organizationSaveInDto.setAddress("Уфа");
        organizationSaveInDto.setIsActive(true);
        organizationSaveInDto.setPhone("89374567891");
        return organizationSaveInDto;

    }

    public static OrganizationUpdateInDto createOrganizationUpdateInDto() {
        OrganizationUpdateInDto organizationUpdateInDto = new OrganizationUpdateInDto();
        organizationUpdateInDto.setId(ConstantValue.ID);
        organizationUpdateInDto.setName("СБЕР");
        organizationUpdateInDto.setInn(INN);
        organizationUpdateInDto.setKpp(KPP);
        organizationUpdateInDto.setIsActive(true);
        organizationUpdateInDto.setFullName("ОАО Сбер");
        organizationUpdateInDto.setAddress("Уфа");
        return organizationUpdateInDto;
    }

    public static OrganisationDtoRequest createOrganisationDtoRequest() {
        OrganisationDtoRequest organisationDtoRequest = new OrganisationDtoRequest();
        organisationDtoRequest.setName("СБЕР");
        return organisationDtoRequest;
    }

    public static Organization createOrganization() {
        Organization organization = new Organization();
        organization.setPhone(PHONE);
        organization.setKpp(KPP);
        organization.setInn(INN);
        organization.setName(NEW_ORGANIZATION);
        organization.setAddress(ADDRESS);
        organization.setFullName(FN_ORGANIZATION);
        organization.setIsActive(true);
        return organization;
    }

    public static OrganizationOutDto createOrganizationOutDto() {
        OrganizationOutDto organizationOutDto = new OrganizationOutDto();
        organizationOutDto.setFullName(FULL_NAME);
        organizationOutDto.setId(ConstantValue.ID);
        organizationOutDto.setAddress(ADDRESS);
        organizationOutDto.setPhone(PHONE);
        organizationOutDto.setName(NAME);
        organizationOutDto.setIsActive(true);
        return organizationOutDto;
    }

    public static OrganizationListOut createOrganizationListOutDto() {
        OrganizationListOut organizationListOut = new OrganizationListOut();
        organizationListOut.setName(NAME);
        organizationListOut.setIsActive(true);
        organizationListOut.setId(ConstantValue.ID);
        return organizationListOut;
    }
}

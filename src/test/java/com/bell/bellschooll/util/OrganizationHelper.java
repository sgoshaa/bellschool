package com.bell.bellschooll.util;

import com.bell.bellschooll.dto.request.OrganisationDtoRequest;
import com.bell.bellschooll.dto.request.OrganizationSaveInDto;
import com.bell.bellschooll.dto.request.OrganizationUpdateInDto;
import com.bell.bellschooll.model.Organization;

/**
 * Класс для помощи при тестировании сервиса организаций
 */
public class OrganizationHelper {

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
        organizationUpdateInDto.setInn(456456789);
        organizationUpdateInDto.setKpp(4567891);
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
        organization.setPhone("2-57-05");
        organization.setKpp(45645646);
        organization.setInn(1565464564);
        organization.setName("new organization");
        organization.setAddress("address");
        organization.setFullName("FN organization");
        organization.setIsActive(true);
        return organization;
    }
}

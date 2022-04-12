package com.bell.bellschooll.util;

import com.bell.bellschooll.dto.request.OrganisationDtoRequest;
import com.bell.bellschooll.dto.request.OrganizationSaveInDto;
import com.bell.bellschooll.dto.request.OrganizationUpdateInDto;

/**
 * Класс для помощи при тестировании сервиса организаций
 */
public class OrganizationRequestHelper {

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

    public static OrganizationUpdateInDto createOrganizationUpdateInDto(){
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

    public static OrganisationDtoRequest createOrganisationDtoRequest(){
        OrganisationDtoRequest organisationDtoRequest = new OrganisationDtoRequest();
        organisationDtoRequest.setName("СБЕР");
        return organisationDtoRequest;
    }
}

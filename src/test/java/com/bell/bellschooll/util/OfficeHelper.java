package com.bell.bellschooll.util;

import com.bell.bellschooll.dto.request.OfficeInListDto;
import com.bell.bellschooll.dto.request.OfficeInSaveDto;
import com.bell.bellschooll.dto.request.OfficeInUpdateDto;
import com.bell.bellschooll.model.Office;
import com.bell.bellschooll.model.Organization;

/**
 * Класс для создания RequestDTO для офиса
 */
public class OfficeHelper {

    public static OfficeInListDto createOfficeInListDto() {
        OfficeInListDto officeInListDto = new OfficeInListDto();
        officeInListDto.setOrgId(ConstantValue.ID);
        return officeInListDto;
    }

    public static OfficeInSaveDto createOfficeInSaveDto() {
        OfficeInSaveDto officeInSaveDto = new OfficeInSaveDto();
        officeInSaveDto.setOrgId(ConstantValue.ID);
        officeInSaveDto.setName("Новый офис");
        officeInSaveDto.setAddress("Уфа");
        officeInSaveDto.setPhone("7891234562");
        officeInSaveDto.setIsActive(true);
        return officeInSaveDto;
    }

    public static OfficeInUpdateDto createOfficeInUpdateDto() {
        OfficeInUpdateDto officeInUpdateDto = new OfficeInUpdateDto();
        officeInUpdateDto.setId(ConstantValue.ID);
        officeInUpdateDto.setName("Обновленный офис");
        officeInUpdateDto.setAddress("Уфа ул.Сверлова 1");
        officeInUpdateDto.setPhone("2-57-05");
        officeInUpdateDto.setIsActive(true);
        return officeInUpdateDto;
    }

    public static Office createOffice(Organization organization) {
        Office office = createOffice();
        office.setOrganization(organization);
        return office;
    }

    public static Office createOffice() {
        Office office = new Office();
        office.setName("name");
        office.setAddress("address");
        office.setIsActive(true);
        office.setOrganization(OrganizationHelper.createOrganization());
        office.setPhone("2-57-05");
        return office;
    }
}

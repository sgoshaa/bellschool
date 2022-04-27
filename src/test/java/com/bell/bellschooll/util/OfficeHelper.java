package com.bell.bellschooll.util;

import com.bell.bellschooll.dto.request.OfficeInListDto;
import com.bell.bellschooll.dto.request.OfficeInSaveDto;
import com.bell.bellschooll.dto.request.OfficeInUpdateDto;
import com.bell.bellschooll.dto.response.OfficeListOutDto;
import com.bell.bellschooll.dto.response.OfficeOutDto;
import com.bell.bellschooll.model.Office;
import com.bell.bellschooll.model.Organization;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для создания RequestDTO для офиса
 */
public class OfficeHelper {

    public static final String UPDATED_OFFICE = "Обновленный офис";
    private static final String ADDRESS = "address";
    public static final String NAME = "name";
    public static final String PHONE = "2-57-05";
    public static final String UPDATED_ADDRESS = "Уфа ул.Сверлова 1";

    public static OfficeInListDto createOfficeInListDto() {
        OfficeInListDto officeInListDto = new OfficeInListDto();
        officeInListDto.setOrgId(ConstantValue.ID);
        return officeInListDto;
    }

    public static OfficeInSaveDto createOfficeInSaveDto() {
        OfficeInSaveDto officeInSaveDto = new OfficeInSaveDto();
        officeInSaveDto.setOrgId(ConstantValue.ID);
        officeInSaveDto.setName(NAME);
        officeInSaveDto.setAddress(ADDRESS);
        officeInSaveDto.setPhone(PHONE);
        officeInSaveDto.setIsActive(true);
        return officeInSaveDto;
    }

    public static OfficeInUpdateDto createOfficeInUpdateDto() {
        OfficeInUpdateDto officeInUpdateDto = new OfficeInUpdateDto();
        officeInUpdateDto.setId(ConstantValue.ID);
        officeInUpdateDto.setName(UPDATED_OFFICE);
        officeInUpdateDto.setAddress(UPDATED_ADDRESS);
        officeInUpdateDto.setPhone(PHONE);
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
        office.setName(NAME);
        office.setAddress(ADDRESS);
        office.setIsActive(true);
        office.setOrganization(OrganizationHelper.createOrganization());
        office.setPhone(PHONE);
        office.setId(ConstantValue.ID);
        return office;
    }

    public static OfficeOutDto createOfficeOutDto() {
        OfficeOutDto officeOutDto = new OfficeOutDto();
        officeOutDto.setAddress(ADDRESS);
        officeOutDto.setName(NAME);
        officeOutDto.setPhone(PHONE);
        officeOutDto.setIsActive(true);
        officeOutDto.setId(ConstantValue.ID);
        return officeOutDto;
    }

    public static List<OfficeListOutDto> createOfficeListOutDto() {
        ArrayList<OfficeListOutDto> officeListOut = new ArrayList<>();
        OfficeListOutDto officeListOutDto = new OfficeListOutDto();
        officeListOutDto.setId(ConstantValue.ID);
        officeListOutDto.setName(NAME);
        officeListOutDto.setIsActive(true);
        officeListOut.add(officeListOutDto);
        return officeListOut;
    }

    public static OfficeListOutDto createSimpleOfficeForListOutDto() {
        OfficeListOutDto officeListOutDto = new OfficeListOutDto();
        officeListOutDto.setId(ConstantValue.ID);
        officeListOutDto.setName(NAME);
        officeListOutDto.setIsActive(true);
        return officeListOutDto;
    }
}

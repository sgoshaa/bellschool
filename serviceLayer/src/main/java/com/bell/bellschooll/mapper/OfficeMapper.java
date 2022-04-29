package com.bell.bellschooll.mapper;

import com.bell.bellschooll.dto.request.OfficeInListDto;
import com.bell.bellschooll.dto.request.OfficeInSaveDto;
import com.bell.bellschooll.dto.request.OfficeInUpdateDto;
import com.bell.bellschooll.dto.response.OfficeListOutDto;
import com.bell.bellschooll.dto.response.OfficeOutDto;
import com.bell.bellschooll.model.Office;
import com.bell.bellschooll.model.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Маппер для Office
 */
@Mapper(componentModel = "spring")
public interface OfficeMapper {
    /**
     * Метод для маппинга из Office в OfficeOutDto
     *
     * @param office
     * @return
     */
    OfficeOutDto officeToDto(Office office);

    /**
     * Метод для маппинга из OfficeInSaveDto в Office
     *
     * @param officeDto
     * @param organization
     * @return
     */
    @Mapping(target = "organization", source = "organization")
    @Mapping(target = "name", source = "officeDto.name")
    @Mapping(target = "phone", source = "officeDto.phone")
    @Mapping(target = "isActive", source = "officeDto.isActive")
    @Mapping(target = "address", source = "officeDto.address")
    @Mapping(target = "version", ignore = true)
    Office dtoToDomain(OfficeInSaveDto officeDto, Organization organization);

    /**
     * Метод для маппинга из Office в OfficeListOutDto
     *
     * @param office
     * @return
     */
    OfficeListOutDto officeToListDto(Office office);

    /**
     * Метод для маппинга из списка офисов в список OfficeListOutDto
     *
     * @param officeList список офисов
     * @return List<OfficeListOutDto>
     */
    List<OfficeListOutDto> toListDto(List<Office> officeList);

    /**
     * метод для маппинга из OfficeInUpdateDto в Office
     *
     * @param officeInUpdateDto
     * @param currentOffice
     * @return
     */
    @Mapping(target = "version", source = "currentOffice.version")
    @Mapping(target = "id", source = "currentOffice.id")
    @Mapping(target = "organization", source = "currentOffice.organization")
    @Mapping(target = "name", source = "officeInUpdateDto.name")
    @Mapping(target = "address", source = "officeInUpdateDto.address")
    @Mapping(target = "phone", source = "officeInUpdateDto.phone")
    @Mapping(target = "isActive", source = "officeInUpdateDto.isActive")
    @Mapping(target = "users", ignore = true)
    Office updateOfficeDtoToDomain(OfficeInUpdateDto officeInUpdateDto, Office currentOffice);




    default Map<String,Object> fromDtoToMap(OfficeInListDto officeInListDto){
        Map<String,Object> map = new HashMap<>();
        if (officeInListDto == null){
            return null;
        }
        if (officeInListDto.getIsActive()!= null){
            map.put("isActive",officeInListDto.getIsActive());
        }
        if (officeInListDto.getName()!= null){
            map.put("name",officeInListDto.getName());
        }
        if (officeInListDto.getPhone()!= null){
            map.put("phone",officeInListDto.getPhone());
        }
        if (officeInListDto.getPhone()!= null){
            map.put("orgId",officeInListDto.getOrgId());
        }
        return new HashMap<>();
    }
}

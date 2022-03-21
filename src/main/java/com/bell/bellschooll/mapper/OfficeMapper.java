package com.bell.bellschooll.mapper;

import com.bell.bellschooll.dto.request.OfficeInSaveDto;
import com.bell.bellschooll.dto.response.OfficeListOutDto;
import com.bell.bellschooll.dto.response.OfficeOutDto;
import com.bell.bellschooll.model.Office;
import com.bell.bellschooll.model.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OfficeMapper {

    OfficeOutDto officeToDto(Office office);

    @Mapping(target="organization",source = "organization")
    @Mapping(target = "name",source = "officeDto.name")
    @Mapping(target = "phone",source = "officeDto.phone")
    @Mapping(target = "isActive",source = "officeDto.isActive")
    @Mapping(target = "address",source = "officeDto.address")
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "version",ignore = true)
    Office dtoToDomain(OfficeInSaveDto officeDto, Organization organization);

    OfficeListOutDto offceToDto(Office office);
}

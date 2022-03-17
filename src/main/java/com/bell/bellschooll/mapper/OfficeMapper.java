package com.bell.bellschooll.mapper;

import com.bell.bellschooll.dto.response.OfficeOutDto;
import com.bell.bellschooll.model.Office;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OfficeMapper {

    OfficeOutDto officeToDto(Office office);
}

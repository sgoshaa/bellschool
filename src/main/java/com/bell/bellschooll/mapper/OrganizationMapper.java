package com.bell.bellschooll.mapper;

import com.bell.bellschooll.dao.OrganizationDao;
import com.bell.bellschooll.dto.request.OrganizationSaveInDto;
import com.bell.bellschooll.dto.request.OrganizationUpdateInDto;
import com.bell.bellschooll.dto.response.OrganizationListOut;
import com.bell.bellschooll.dto.response.OrganizationOutDto;
import com.bell.bellschooll.model.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrganizationMapper {
    OrganizationOutDto organizationToDto(Organization organization);

    OrganizationListOut organizationToListDto(Organization organization);

    Organization organizationInToDomain(OrganizationSaveInDto organizationSaveInDto);

    @Mapping(target = "version",ignore = true)
    Organization organizationInToDomainUpdate(OrganizationUpdateInDto organizationUpdateInDto);
}

package com.bell.bellschooll.mapper;

import com.bell.bellschooll.dao.OrganizationDao;
import com.bell.bellschooll.dto.response.OrganizationListOut;
import com.bell.bellschooll.dto.response.OrganizationOutDto;
import com.bell.bellschooll.model.Organization;
import org.mapstruct.Mapper;

@Mapper
public interface OrganizationMapper {
    OrganizationOutDto organizationToDto(Organization organization);

    OrganizationListOut organizationToListDto(Organization organization);
}

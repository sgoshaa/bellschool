package com.bell.bellschooll.mapper;

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

    @Mapping(target = "offices",ignore = true)
    @Mapping(target = "version",source = "currentOrganization.version")
    @Mapping(target = "id",source = "currentOrganization.id")
    @Mapping(target = "name",expression = "java(equalsData(currentOrganization.getName(),organizationUpdateInDto.getName()))")
    @Mapping(target = "fullName",expression = "java(equalsData(currentOrganization.getFullName(),organizationUpdateInDto.getFullName()))")
    @Mapping(target = "inn",expression = "java(equalsData(currentOrganization.getInn(),organizationUpdateInDto.getInn()))")
    @Mapping(target = "kpp",expression = "java(equalsData(currentOrganization.getKpp(),organizationUpdateInDto.getKpp()))")
    @Mapping(target = "address",expression = "java(equalsData(currentOrganization.getAddress(),organizationUpdateInDto.getAddress()))")
    @Mapping(target = "phone",expression = "java(equalsData(currentOrganization.getPhone(),organizationUpdateInDto.getPhone()))")
    @Mapping(target = "isActive",expression = "java(equalsData(currentOrganization.getIsActive(),organizationUpdateInDto.getIsActive()))")
    Organization organizationInToDomainUpdate(OrganizationUpdateInDto organizationUpdateInDto,Organization currentOrganization);


    default String equalsData(String field, String updateField){
        if (!field.equals(updateField) && !updateField.isEmpty()){
            field = updateField;
        }
        return field;
    }
    default Integer equalsData(Integer field, Integer updateField){
        if (!field.equals(updateField)){
            field = updateField;
        }
        return field;
    }
    default Boolean equalsData(Boolean field, Boolean updateField){
        if (!field.equals(updateField)){
            field = updateField;
        }
        return field;
    }
}

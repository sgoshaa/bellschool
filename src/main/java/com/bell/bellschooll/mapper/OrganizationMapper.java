package com.bell.bellschooll.mapper;

import com.bell.bellschooll.dto.request.OrganizationSaveInDto;
import com.bell.bellschooll.dto.request.OrganizationUpdateInDto;
import com.bell.bellschooll.dto.response.OrganizationListOut;
import com.bell.bellschooll.dto.response.OrganizationOutDto;
import com.bell.bellschooll.model.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.http.ResponseEntity;

/**
 * Маппер для работы с Organization
 */
@Mapper(componentModel = "spring")
public interface OrganizationMapper {
    /**
     * Метод для маппинга из Organization в OrganizationOutDto
     *
     * @param organization
     * @return
     */
    OrganizationOutDto organizationToDto(Organization organization);

    /**
     * Метод для маппинза из Organization в OrganizationListOut
     *
     * @param organization
     * @return
     */
    OrganizationListOut organizationToListDto(Organization organization);

    /**
     * Метод для маппинза из OrganizationSaveInDto в Organization
     *
     * @param organizationSaveInDto
     * @return
     */
    Organization organizationInToDomain(OrganizationSaveInDto organizationSaveInDto);

    /**
     * Метод для маппинза из OrganizationUpdateInDto в Organization
     *
     * @param organizationUpdateInDto
     * @param currentOrganization
     * @return
     */
    @Mapping(target = "offices", ignore = true)
    @Mapping(target = "version", source = "currentOrganization.version")
    @Mapping(target = "id", source = "currentOrganization.id")
    @Mapping(target = "name", expression = "java(equalsData(currentOrganization.getName(),organizationUpdateInDto.getName()))")
    @Mapping(target = "fullName", expression = "java(equalsData(currentOrganization.getFullName(),organizationUpdateInDto.getFullName()))")
    @Mapping(target = "inn", expression = "java(equalsData(currentOrganization.getInn(),organizationUpdateInDto.getInn()))")
    @Mapping(target = "kpp", expression = "java(equalsData(currentOrganization.getKpp(),organizationUpdateInDto.getKpp()))")
    @Mapping(target = "address", expression = "java(equalsData(currentOrganization.getAddress(),organizationUpdateInDto.getAddress()))")
    @Mapping(target = "phone", expression = "java(equalsData(currentOrganization.getPhone(),organizationUpdateInDto.getPhone()))")
    @Mapping(target = "isActive", expression = "java(equalsData(currentOrganization.getIsActive(),organizationUpdateInDto.getIsActive()))")
    Organization organizationInToDomainUpdate(OrganizationUpdateInDto organizationUpdateInDto, Organization currentOrganization);


    default String equalsData(String field, String updateField) {
        if (updateField == null) {
            return field;
        }
        if (!field.equals(updateField) && !updateField.isEmpty()) {
            field = updateField;
        }
        return field;
    }

    default Integer equalsData(Integer field, Integer updateField) {
        if (!field.equals(updateField)) {
            field = updateField;
        }
        return field;
    }

    default Boolean equalsData(Boolean field, Boolean updateField) {
        if (!field.equals(updateField)) {
            field = updateField;
        }
        return field;
    }
}

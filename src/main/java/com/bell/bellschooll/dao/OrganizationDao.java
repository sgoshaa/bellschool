package com.bell.bellschooll.dao;

import com.bell.bellschooll.dto.request.OrganisationDtoRequest;
import com.bell.bellschooll.model.Organization;

import java.util.List;

/**
 * DAO для работы с Organization
 */
public interface OrganizationDao {

    Organization getOrganizationById(Integer id);


    List<Organization> getListOrganizationByName(OrganisationDtoRequest organisationDtoRequest);

}

package com.bell.bellschooll.dao;

import com.bell.bellschooll.dto.request.OrganisationDtoRequest;
import com.bell.bellschooll.exception.ErrorException;
import com.bell.bellschooll.model.Organization;
import com.bell.bellschooll.util.OrganizationHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
class OrganizationDaoImplTest {

    @Autowired
    OrganizationDaoImpl organizationDao;

    @Test
    @Sql("/addNewOrganizationId10005.sql")
    void getOrganizationById() {
        Organization organization = organizationDao.getOrganizationById(10005);
        assertNotNull(organization);
        assertEquals(10005, organization.getId());
    }

    @Test
    void getListOrganizationByName() {
        OrganisationDtoRequest organisationDtoRequest = OrganizationHelper.createOrganisationDtoRequest();
        List<Organization> listOrganizationByName = organizationDao.getListOrganizationByOrganizationDtoRequest(organisationDtoRequest);

        assertFalse(listOrganizationByName.isEmpty());
        assertThat(listOrganizationByName.stream()
                .map(Organization::getName)
                .collect(Collectors.toList()
                ), hasItem(organisationDtoRequest.getName()));
    }

    @Test
    void getListOrganizationByNameFailEmptyName() {
        OrganisationDtoRequest organisationDtoRequest = OrganizationHelper.createOrganisationDtoRequest();
        organisationDtoRequest.setName("");
        assertThrows(ErrorException.class, () -> organizationDao.getListOrganizationByOrganizationDtoRequest(organisationDtoRequest));
    }

    @Test
    void getListOrganizationByNameAnyName() {
        OrganisationDtoRequest organisationDtoRequest = OrganizationHelper.createOrganisationDtoRequest();
        organisationDtoRequest.setName("name");
        List<Organization> listOrganizationByName = organizationDao.getListOrganizationByOrganizationDtoRequest(organisationDtoRequest);

        assertTrue(listOrganizationByName.isEmpty());
    }

    @Test
    void getListOrganizationByNameAndInn() {
        OrganisationDtoRequest organisationDtoRequest = OrganizationHelper.createOrganisationDtoRequest();
        organisationDtoRequest.setInn(456123787);
        List<Organization> listOrganizationByName = organizationDao.getListOrganizationByOrganizationDtoRequest(organisationDtoRequest);

        assertFalse(listOrganizationByName.isEmpty());
        assertThat(listOrganizationByName.stream()
                .map(Organization::getInn)
                .collect(Collectors.toList()
                ), hasItem(organisationDtoRequest.getInn()));
    }

    @Test
    void getListOrganizationByNameAndInnAndIsActive() {
        OrganisationDtoRequest organisationDtoRequest = OrganizationHelper.createOrganisationDtoRequest();
        organisationDtoRequest.setInn(456123787);
        organisationDtoRequest.setIsActive(true);
        List<Organization> listOrganizationByName = organizationDao.getListOrganizationByOrganizationDtoRequest(organisationDtoRequest);

        assertFalse(listOrganizationByName.isEmpty());
        assertThat(listOrganizationByName.stream()
                        .filter(organization -> organization.getInn().equals(organisationDtoRequest.getInn()))
                        .findFirst().get()
                , hasProperty("name", equalTo(organisationDtoRequest.getName())));
    }

    @Test
    void getListOrganizationByNameAndIsActive() {
        OrganisationDtoRequest organisationDtoRequest = OrganizationHelper.createOrganisationDtoRequest();
        organisationDtoRequest.setIsActive(true);
        List<Organization> listOrganizationByName = organizationDao.getListOrganizationByOrganizationDtoRequest(organisationDtoRequest);

        assertFalse(listOrganizationByName.isEmpty());
        assertThat(listOrganizationByName.stream()
                        .filter(organization -> organization.getIsActive() == organisationDtoRequest.getIsActive())
                        .findFirst().get()
                , hasProperty("name", equalTo(organisationDtoRequest.getName())));
    }

    @Test
    void save() {
        Organization organization = OrganizationHelper.createOrganization();
        organizationDao.save(organization);
        Organization organizationById = organizationDao.getOrganizationById(organization.getId());

        assertNotNull(organizationById);
        assertEquals(organization.getFullName(), organizationById.getFullName());
        assertEquals(organization.getName(), organizationById.getName());
        assertEquals(organization.getAddress(), organizationById.getAddress());
        assertEquals(organization.getPhone(), organizationById.getPhone());
        assertEquals(organization.getIsActive(), organizationById.getIsActive());
        assertEquals(organization.getInn(), organizationById.getInn());
        assertEquals(organization.getKpp(), organizationById.getKpp());
    }

    @Test
    void update() {
        Organization organizationById = organizationDao.getOrganizationById(1);

        assertNotNull(organizationById);

        organizationById.setName("updateName");
        organizationDao.update(organizationById);
        Organization updateOrganization = organizationDao.getOrganizationById(1);

        assertNotNull(updateOrganization);
        assertEquals(organizationById.getName(), updateOrganization.getName());
    }
}
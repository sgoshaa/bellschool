package com.bell.bellschooll.repository;

import com.bell.bellschooll.util.ConstantValue;
import com.bell.bellschooll.util.OrganizationHelper;
import com.bell.bellschooll.dto.request.OrganisationDtoRequest;
import com.bell.bellschooll.model.Organization;
import com.bell.bellschooll.specification.OrganizationSpecification;
import org.junit.jupiter.api.DisplayName;
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
class OrganizationRepositoryTest {

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    OrganizationSpecification organizationSpecification;

    @Test
    @Sql("/addNewOrganizationId10005.sql")//Given
    @DisplayName("Метод проверяет работу метода getById() с 10005 ")
    void getOrganizationById() {
        //When
        Organization organization = organizationRepository.getById(10005);

        //Then
        assertNotNull(organization);
        assertEquals(10005, organization.getId());
    }

    @Test
    @DisplayName("Тест проверяет работу метода findAll() c параметром name")
    void getListOrganizationByName() {
        //Given
        OrganisationDtoRequest organisationDtoRequest = OrganizationHelper.createOrganisationDtoRequest();

        //When
        List<Organization> listOrganization = organizationRepository
                .findAll(organizationSpecification.getSpecification(organisationDtoRequest));

        //Then
        assertFalse(listOrganization.isEmpty());
        assertThat(listOrganization.stream()
                        .map(Organization::getName)
                        .collect(Collectors.toList())
                , hasItem(organisationDtoRequest.getName()));
    }

    @Test
    @DisplayName("Тест проверяет работу метода findAll с параметром ,которого нет в базе ")
    void getListOrganizationByNameAnyName() {
        //Given
        OrganisationDtoRequest organisationDtoRequest = OrganizationHelper.createOrganisationDtoRequest();
        organisationDtoRequest.setName("name");

        //When
        List<Organization> listOrganization = organizationRepository
                .findAll(organizationSpecification.getSpecification(organisationDtoRequest));
        //Then
        assertTrue(listOrganization.isEmpty());
    }

    @Test
    @DisplayName("Тест проверяет работу метода findAll с параметрами name and inn")
    void getListOrganizationByNameAndInn() {
        //Given
        OrganisationDtoRequest organisationDtoRequest = OrganizationHelper.createOrganisationDtoRequest();
        organisationDtoRequest.setInn(456123787);

        //When
        List<Organization> listOrganization = organizationRepository
                .findAll(organizationSpecification.getSpecification(organisationDtoRequest));

        //Then
        assertFalse(listOrganization.isEmpty());
        assertThat(listOrganization.stream()
                .map(Organization::getInn)
                .collect(Collectors.toList()
                ), hasItem(organisationDtoRequest.getInn()));
        assertThat(listOrganization.stream()
                .map(Organization::getName)
                .collect(Collectors.toList()
                ), hasItem(organisationDtoRequest.getName()));
    }

    @Test
    @DisplayName("Тест проверяет работу метода findAll() с параметрами name and inn and isActive")
    void getListOrganizationByNameAndInnAndIsActive() {
        //Given
        OrganisationDtoRequest organisationDtoRequest = OrganizationHelper.createOrganisationDtoRequest();
        organisationDtoRequest.setInn(456123787);
        organisationDtoRequest.setIsActive(true);

        //When
        List<Organization> listOrganization = organizationRepository
                .findAll(organizationSpecification.getSpecification(organisationDtoRequest));

        //Then
        assertFalse(listOrganization.isEmpty());
        assertThat(listOrganization.stream()
                        .filter(organization -> organization.getInn().equals(organisationDtoRequest.getInn()))
                        .findFirst().get()
                , hasProperty("name", equalTo(organisationDtoRequest.getName())));

        listOrganization.forEach(organization -> {
            assertEquals(organization.getName(), organisationDtoRequest.getName());
            assertEquals(organization.getIsActive(), organisationDtoRequest.getIsActive());
            assertEquals(organization.getInn(), organisationDtoRequest.getInn());
        });
    }

    @Test
    @DisplayName("Тест проверяет работу метода findAll() с параметрами name and isActive")
    void getListOrganizationByNameAndIsActive() {
        //Given
        OrganisationDtoRequest organisationDtoRequest = OrganizationHelper.createOrganisationDtoRequest();
        organisationDtoRequest.setIsActive(true);

        //When
        List<Organization> listOrganization = organizationRepository
                .findAll(organizationSpecification.getSpecification(organisationDtoRequest));

        //Then
        assertFalse(listOrganization.isEmpty());
        assertThat(listOrganization.stream()
                        .findFirst().get()
                , hasProperty("name", equalTo(organisationDtoRequest.getName())));

        listOrganization.forEach(organization -> {
            assertEquals(organization.getIsActive(),organisationDtoRequest.getIsActive());
            assertEquals(organization.getName(),organisationDtoRequest.getName());
        });
    }

    @Test
    @DisplayName("Тест проверяет работу метода save()")
    void save() {
        //Given
        Organization organization = OrganizationHelper.createOrganization();

        //When
        Organization savedOrg = organizationRepository.save(organization);
        Organization organizationById = organizationRepository.getById(organization.getId());

        //Then
        assertNotNull(organizationById);
        assertEquals(savedOrg.getFullName(), organizationById.getFullName());
        assertEquals(savedOrg.getName(), organizationById.getName());
        assertEquals(savedOrg.getAddress(), organizationById.getAddress());
        assertEquals(savedOrg.getPhone(), organizationById.getPhone());
        assertEquals(savedOrg.getIsActive(), organizationById.getIsActive());
        assertEquals(savedOrg.getInn(), organizationById.getInn());
        assertEquals(savedOrg.getKpp(), organizationById.getKpp());
    }

    @Test
    @DisplayName("Тест проверяет работу метода обновления")
    void update() {
        //Given
        Organization organizationById = organizationRepository.getById(ConstantValue.ID);
        organizationById.setName("updateName");

        //When
        Organization expectedOrg = organizationRepository.save(organizationById);
        Organization actualOrg = organizationRepository.getById(expectedOrg.getId());

        //Then
        assertNotNull(actualOrg);
        assertEquals(expectedOrg.getName(), actualOrg.getName());
    }
}
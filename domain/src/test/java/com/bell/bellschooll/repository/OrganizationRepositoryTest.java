package com.bell.bellschooll.repository;

import com.bell.bellschooll.model.Organization;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.google.common.base.Predicates.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
class OrganizationRepositoryTest {

    @Autowired
    OrganizationRepository organizationRepository;

    @Test
    @Sql("/addNewOrganizationId10005.sql")//Given
    @DisplayName("Метод проверяет работу метода getById() с 10005 ")
    void getOrganizationById() {
        //When
        Optional<Organization> organization = organizationRepository.findById(10005);

        //Then
        assertTrue(organization.isPresent());
        assertNotNull(organization);
        assertEquals(10005, organization.get().getId());
    }

    @Test
    @DisplayName("Тест проверяет работу метода findAll() c параметром name")
    void getListOrganizationByName() {
        //When
        List<Organization> listOrganization = organizationRepository
                .findAll(getSpecification("BELL",null,null));

        //Then
        assertFalse(listOrganization.isEmpty());
        assertThat(listOrganization.stream()
                        .map(Organization::getName)
                        .collect(Collectors.toList())
                , hasItem("BELL"));
    }

    @Test
    @DisplayName("Тест проверяет работу метода findAll с параметром ,которого нет в базе ")
    void getListOrganizationByNameAnyName() {
        //When
        List<Organization> listOrganization = organizationRepository
                .findAll(getSpecification("name",null,null));
        //Then
        assertTrue(listOrganization.isEmpty());
    }

    @Test
    @DisplayName("Тест проверяет работу метода findAll с параметрами name and inn")
    void getListOrganizationByNameAndInn() {
//        //Given
//        OrganisationDtoRequest organisationDtoRequest = OrganizationHelper.createOrganisationDtoRequest();
//        organisationDtoRequest.setInn(456123787);
//
//        //When
//        List<Organization> listOrganization = organizationRepository
//                .findAll(organizationSpecification.getSpecification(organisationDtoRequest));
//
//        //Then
//        assertFalse(listOrganization.isEmpty());
//        assertThat(listOrganization.stream()
//                .map(Organization::getInn)
//                .collect(Collectors.toList()
//                ), hasItem(organisationDtoRequest.getInn()));
//        assertThat(listOrganization.stream()
//                .map(Organization::getName)
//                .collect(Collectors.toList()
//                ), hasItem(organisationDtoRequest.getName()));
    }

    @Test
    @DisplayName("Тест проверяет работу метода findAll() с параметрами name and inn and isActive")
    void getListOrganizationByNameAndInnAndIsActive() {
//        //Given
//
//        int inn = 456123787;
//
//        //When
//        String name = "СБЕР";
//        List<Organization> listOrganization = organizationRepository
//                .findAll(getSpecification(name,true,inn));
//
//        //Then
//        assertFalse(listOrganization.isEmpty());
//        listOrganization.forEach(organization -> {
//            assertEquals(organization.getName(), name);
//            assertEquals(organization.getIsActive(), true);
//            assertEquals(organization.getInn(), inn);
//        });
    }

    @Test
    @DisplayName("Тест проверяет работу метода findAll() с параметрами name and isActive")
    void getListOrganizationByNameAndIsActive() {
//        //Given
//        OrganisationDtoRequest organisationDtoRequest = OrganizationHelper.createOrganisationDtoRequest();
//        organisationDtoRequest.setIsActive(true);
//
//        //When
//        List<Organization> listOrganization = organizationRepository
//                .findAll(organizationSpecification.getSpecification(organisationDtoRequest));
//
//        //Then
//        assertFalse(listOrganization.isEmpty());
//        assertThat(listOrganization.stream()
//                        .findFirst().get()
//                , hasProperty("name", equalTo(organisationDtoRequest.getName())));
//
//        listOrganization.forEach(organization -> {
//            assertEquals(organization.getIsActive(),organisationDtoRequest.getIsActive());
//            assertEquals(organization.getName(),organisationDtoRequest.getName());
//        });
    }

    @Test
    @DisplayName("Тест проверяет работу метода save()")
    void save() {
        //Given
        Organization organization = new Organization();
        organization.setAddress("address");
        organization.setFullName("full_name");
        organization.setInn(1111111);
        organization.setKpp(2222222);
        organization.setIsActive(true);
        organization.setName("name");
        organization.setPhone("phone");

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
        Organization organizationById = organizationRepository.getById(1);
        organizationById.setName("updatedName");

        //When
        Organization expectedOrg = organizationRepository.save(organizationById);
        Organization actualOrg = organizationRepository.getById(expectedOrg.getId());

        //Then
        assertNotNull(actualOrg);
        assertEquals(expectedOrg, actualOrg);
    }

    private Specification<Organization> getSpecification(String name, Boolean isActive, Integer inn) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            predicateList.add(criteriaBuilder.equal(root.get("name"), name));
            if (isActive != null) {
                predicateList.add(criteriaBuilder.equal(root.get("isActive"), isActive));
            }
            if (inn != null) {
                predicateList.add(criteriaBuilder.equal(root.get("inn"), inn));
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        };
    }
}
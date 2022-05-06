package com.bell.bellschooll.repository;

import com.bell.bellschooll.model.Organization;
import com.bell.bellschooll.util.OrganizationHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
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

    @DisplayName("Тест проверяет работу метода findAll() c параметрами")
    @ParameterizedTest
    @CsvSource({
            "BELL,,",
            "BELL,true,",
            "BELL,true,123456789"
    })
    void getListOrganization(String name, Boolean isActive, Integer inn) {
        //When
        List<Organization> listOrganization = organizationRepository
                .findAll(getSpecification(name, isActive, inn));

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
                .findAll(getSpecification("name", null, null));
        //Then
        assertTrue(listOrganization.isEmpty());
    }

    @Test
    @DisplayName("Тест проверяет работу метода save()")
    void save() {
        //Given
        Organization organization = OrganizationHelper.getNewOrganization();

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
package com.bell.bellschooll.repository;

import com.bell.bellschooll.model.Office;
import com.bell.bellschooll.util.OfficeHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
class OfficeRepositoryTest {

    @Autowired
    OfficeRepository officeRepository;

    @Autowired
    OrganizationRepository organizationRepository;

    @Test
    void getById() {
        Optional<Office> officeById = officeRepository.findById(1);
        assertTrue(officeById.isPresent());
        assertEquals(1, officeById.get().getId());
    }

    @Test
    void saveOffice() {
        //Given
        Office office = OfficeHelper.createOffice(organizationRepository.findById(1).get());
        Office savedOffice = officeRepository.save(office);

        //When
        Optional<Office> officeById = officeRepository.findById(savedOffice.getId());

        //Then
        assertTrue(officeById.isPresent());
        assertEquals(office.getId(), officeById.get().getId());
        assertEquals(office.getName(), officeById.get().getName());
        assertEquals(office.getAddress(), officeById.get().getAddress());
        assertEquals(office.getIsActive(), officeById.get().getIsActive());
        assertEquals(office.getOrganization(), officeById.get().getOrganization());
        assertEquals(office.getPhone(), officeById.get().getPhone());
    }

    @ParameterizedTest
    @CsvSource({
            ",,,1",
            ",true,,1",
            "офис Bell1,true,,1",
            "офис Bell1,,,1",
            "офис Bell1,true,12321321321,1"
    })
    void getListOffice(String name, Boolean isActive, String phone, Integer orgId) {
        //When
        List<Office> listOffice = officeRepository.findAll(getSpecification(name, isActive, phone, orgId));

        //Then
        assertFalse(listOffice.isEmpty());
        assertThat(listOffice.stream()
                        .map(Office::getName)
                        .collect(Collectors.toList())
                , hasItem("офис Bell1"));
    }

    @Test
    void updateOffice() {
        //Given
        Office officeById = officeRepository.getById(1);
        officeById.setName("UpdatedName");

        //When
        Office updatedOffice = officeRepository.save(officeById);

        //Then
        Office actualOffice = officeRepository.getById(updatedOffice.getId());

        assertNotNull(actualOffice);
        assertEquals(officeById, actualOffice);
    }

    private Specification<Office> getSpecification(String name, Boolean isActive, String phone, Integer orgId) {
        return (root, query, criteriaBuilder) -> {
            ArrayList<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("organization").get("id"), orgId));
            if (name != null) {
                predicates.add(criteriaBuilder.equal(root.get("name"), name));
            }
            if (isActive != null) {
                predicates.add(criteriaBuilder.equal(root.get("isActive"), isActive));
            }
            if (phone != null) {
                predicates.add(criteriaBuilder.equal(root.get("phone"), phone));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
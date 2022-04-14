package com.bell.bellschooll.service;

import com.bell.bellschooll.dto.request.OfficeInListDto;
import com.bell.bellschooll.dto.response.OfficeListOutDto;
import com.bell.bellschooll.dto.response.OfficeOutDto;
import com.bell.bellschooll.dto.response.SuccessDto;
import com.bell.bellschooll.exception.ErrorException;
import com.bell.bellschooll.util.ConstantValue;
import com.bell.bellschooll.util.OfficeHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class OfficeServiceTest {

    @Autowired
    OfficeService officeService;

    @Test
    void getOfficeById() {
        ResponseEntity<OfficeOutDto> office = officeService.getOfficeById(ConstantValue.ID);
        assertNotNull(office);
        assertEquals(ConstantValue.ID, office.getBody().getId());
    }

    @Test
    void addOffice() {
        ResponseEntity<SuccessDto> successDtoResponseEntity = officeService.addOffice(OfficeHelper.createOfficeInSaveDto());
        assertEquals(ConstantValue.RESULT, successDtoResponseEntity.getBody().getResult());
    }

    @Test
    void listOffice() {
        ResponseEntity<List<OfficeListOutDto>> listResponseEntity = officeService.listOffice(OfficeHelper.createOfficeInListDto());
        assertTrue(listResponseEntity.getBody().size() > 0);
    }

    @Test
    void updateOffice() {
        ResponseEntity<SuccessDto> successDtoResponseEntity = officeService.updateOffice(OfficeHelper.createOfficeInUpdateDto());
        assertEquals(ConstantValue.RESULT, successDtoResponseEntity.getBody().getResult());
    }

    @Test
    void getOffice() {
        assertThrows(ErrorException.class, () -> officeService.getOffice(1035));
    }

    @Test
    @Sql("/addNewOrganizationId10005.sql")
    void listOfficeEmptyList() {
        OfficeInListDto officeInListDto = OfficeHelper.createOfficeInListDto();
        officeInListDto.setOrgId(10005);
        assertThrows(ErrorException.class, () -> officeService.listOffice(officeInListDto));
    }
}
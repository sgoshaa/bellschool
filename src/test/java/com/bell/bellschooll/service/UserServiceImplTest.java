package com.bell.bellschooll.service;

import com.bell.bellschooll.dao.CountryDao;
import com.bell.bellschooll.dao.DocumentTypeDao;
import com.bell.bellschooll.dao.UserDao;
import com.bell.bellschooll.dto.request.UpdateUserInDto;
import com.bell.bellschooll.dto.request.UserInSaveDto;
import com.bell.bellschooll.dto.response.SuccessDto;
import com.bell.bellschooll.dto.response.UserOutDto;
import com.bell.bellschooll.dto.response.UserOutListDto;
import com.bell.bellschooll.exception.ErrorException;
import com.bell.bellschooll.mapper.DocumentMapper;
import com.bell.bellschooll.mapper.UserMapper;
import com.bell.bellschooll.model.Country;
import com.bell.bellschooll.model.Document;
import com.bell.bellschooll.model.DocumentType;
import com.bell.bellschooll.model.Office;
import com.bell.bellschooll.model.Organization;
import com.bell.bellschooll.model.User;
import com.bell.bellschooll.util.ConstantValue;
import com.bell.bellschooll.util.OfficeHelper;
import com.bell.bellschooll.util.OrganizationHelper;
import com.bell.bellschooll.util.UserHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
class UserServiceImplTest {

    @Autowired
    UserService userService;

    @MockBean
    UserDao userDao;
    @Autowired
    UserMapper userMapper;
    @Autowired
    DocumentMapper documentMapper;
    @MockBean
    OfficeService officeService;
    @MockBean
    CountryDao countryDao;
    @MockBean
    DocumentTypeDao documentTypeDao;


    @Test
    @Transactional
    void addUser() {
        UserInSaveDto userInSaveDto = UserHelper.createUserInSaveDto();
        DocumentType documentType = new DocumentType();
        documentType.setId(1);
        documentType.setName("тип документа");
        documentType.setCode("21");
        documentType.setVersion(1);

        when(documentTypeDao.getDocumentTypeByCode(userInSaveDto.getDocCode())).thenReturn(documentType);
        Country country = new Country();
        country.setCode(643);
        country.setName("Страна");
        country.setId(1);
        when(countryDao.getCountryByCode(643)).thenReturn(country);

        Organization organization = OrganizationHelper.createOrganization();
        organization.setId(1);

        Office office = new Office();
        office.setId(1);
        office.setOrganization(organization);
        office.setName("name");

        when(officeService.getOffice(userInSaveDto.getOfficeId())).thenReturn(office);

        User user = userMapper.dtoToDomain(userInSaveDto);

        user.setOffice(office);
        user.setCountry(country);

        Document document = new Document();
        document.setUser(user);
        document.setDocType(documentType);
        document.setDocNumber(userInSaveDto.getDocNumber());
        document.setDocDate(userInSaveDto.getDocDate());
//        user.setDocument(document);

        doNothing().when(userDao).addUser(user);

        ResponseEntity<SuccessDto> successDtoResponseEntity = userService.addUser(userInSaveDto);

        verify(userDao).addUser(user);

        assertEquals(ConstantValue.RESULT, successDtoResponseEntity.getBody().getResult());
    }

    @Test
    void addUserFailDocumentType() {
        assertThrows(ErrorException.class, () -> userService.addUser(UserHelper.createUserInSaveDto("1005")));
    }

    @Test
    void addUserFailCountryCode() {
        assertThrows(ErrorException.class, () -> userService.addUser(UserHelper.createUserInSaveDto(1005)));
    }

    @Test
    void getUser() {
        ResponseEntity<UserOutDto> user = userService.getUser(ConstantValue.ID);
        assertNotNull(user);
        assertEquals(ConstantValue.ID, Objects.requireNonNull(user.getBody()).getId());
    }

    @Test
    void getUserFail() {
        assertThrows(ErrorException.class, () -> userService.getUser(10005));
    }

    @Test
    void getListUser() {
        List<UserOutListDto> listUser = userService.getListUser(UserHelper.createUserInListDto()).getBody();
        assertNotNull(listUser);
        assertTrue(listUser.size() > 0);
        UserOutListDto userOutListDto = listUser.stream().findFirst().get();
        assertThat(userOutListDto, hasProperty("firstName", equalTo("Спирин")));
    }

    @Test
    void updateUser() {
        UpdateUserInDto updateUserInDto = UserHelper.createUpdateUserInDto();
        ResponseEntity<SuccessDto> successDtoResponseEntity = userService.updateUser(updateUserInDto);
        assertEquals(ConstantValue.RESULT, Objects.requireNonNull(successDtoResponseEntity.getBody()).getResult());

        UserOutDto user = userService.getUser(updateUserInDto.getId()).getBody();

        assert user != null;

        assertEquals(updateUserInDto.getId(), user.getId());
        assertEquals(updateUserInDto.getFirstName(), user.getFirstName());
        assertEquals(updateUserInDto.getPosition(), user.getPosition());
    }
}
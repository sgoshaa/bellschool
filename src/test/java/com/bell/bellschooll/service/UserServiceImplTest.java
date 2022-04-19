package com.bell.bellschooll.service;

import com.bell.bellschooll.dao.CountryDao;
import com.bell.bellschooll.dao.DocumentTypeDao;
import com.bell.bellschooll.dao.UserDao;
import com.bell.bellschooll.dto.request.UpdateUserInDto;
import com.bell.bellschooll.dto.request.UserInListDto;
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

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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
        //Given
        UserInSaveDto userInSaveDto = UserHelper.createUserInSaveDto();
        DocumentType documentType = UserHelper.createDocumentTypeForTest();
        Country country = UserHelper.createCountryForTestUser();
        Office office = OfficeHelper.createOffice();

        User user = userMapper.dtoToDomain(userInSaveDto);
        user.setOffice(office);
        user.setCountry(country);

        Document document = documentMapper.dtoToDomain(userInSaveDto);
        document.setDocType(documentType);
        document.setUser(user);

        user.setDocument(document);

        when(countryDao.getCountryByCode(643)).thenReturn(country);
        when(documentTypeDao.getDocumentTypeByCode(userInSaveDto.getDocCode())).thenReturn(documentType);
        when(officeService.getOffice(userInSaveDto.getOfficeId())).thenReturn(office);

        //When
        ResponseEntity<SuccessDto> successDtoResponseEntity = userService.addUser(userInSaveDto);

        //Then
        verify(userDao).addUser(user);

        assertEquals(ConstantValue.RESULT, successDtoResponseEntity.getBody().getResult());
    }

    @Test
    @Transactional
    void addUserFailDocumentType() {
        String failDocCode = "1005";
        when(documentTypeDao.getDocumentTypeByCode(failDocCode)).thenReturn(null);
        assertThrows(ErrorException.class, () -> userService.addUser(UserHelper.createUserInSaveDto(failDocCode)));
    }

    @Test
    @Transactional
    void addUserFailCountryCode() {
        //Given
        UserInSaveDto userInSaveDto = UserHelper.createUserInSaveDto();
        DocumentType documentType = UserHelper.createDocumentTypeForTest();
        Country country = UserHelper.createCountryForTestUser();
        Organization organization = OrganizationHelper.createOrganization();
        organization.setId(1);
        Office office = OfficeHelper.createOffice();

        User user = userMapper.dtoToDomain(userInSaveDto);
        user.setOffice(office);
        user.setCountry(country);

        Document document = documentMapper.dtoToDomain(userInSaveDto);
        document.setDocType(documentType);
        document.setUser(user);

        user.setDocument(document);

        when(documentTypeDao.getDocumentTypeByCode(userInSaveDto.getDocCode())).thenReturn(documentType);
        when(officeService.getOffice(userInSaveDto.getOfficeId())).thenReturn(office);

        int failCountryCode = 1005;
        when(countryDao.getCountryByCode(failCountryCode)).thenReturn(null);
        //Then
        assertThrows(ErrorException.class, () -> userService.addUser(UserHelper.createUserInSaveDto(failCountryCode)));
    }

    @Test
    void getUser() {
        User user = UserHelper.createUser();
        user.setId(ConstantValue.ID);
        //Given
        when(userDao.getUserById(user.getId())).thenReturn(user);

        //When
        ResponseEntity<UserOutDto> userServiceResponse = userService.getUser(ConstantValue.ID);

        //Then
        verify(userDao).getUserById(ConstantValue.ID);
        UserOutDto userOutDto = userServiceResponse.getBody();
        assertNotNull(userServiceResponse);
        assertEquals(ConstantValue.ID, userOutDto.getId());
        assertEquals(user.getFirstName(), userOutDto.getFirstName());
        assertEquals(user.getSecondName(), userOutDto.getSecondName());
        assertEquals(user.getMiddleName(), userOutDto.getMiddleName());
    }

    @Test
    void getUserFail() {
        int failIdUser = 10005;
        when(userDao.getUserById(failIdUser)).thenReturn(null);
        assertThrows(ErrorException.class, () -> userService.getUser(failIdUser));
        verify(userDao).getUserById(failIdUser);
    }

    @Test
    void getListUser() {
        //Given
        User first = UserHelper.createUser();
        first.setFirstName("first");
        User second = UserHelper.createUser();
        second.setFirstName("second");

        List<User> users = List.of(first, second);
        UserInListDto userInListDto = UserHelper.createUserInListDto();
        Office office = OfficeHelper.createOffice();

        when(officeService.getOffice(ConstantValue.ID)).thenReturn(office);
        when(userDao.getListUser(userInListDto, office)).thenReturn(users);

        //When
        ResponseEntity<List<UserOutListDto>> listUser = userService.getListUser(userInListDto);

        //Then
        verify(userDao).getListUser(userInListDto, office);
        verify(officeService).getOffice(ConstantValue.ID);
        assertNotNull(listUser);
        assertTrue(listUser.getBody().size() == users.size());
        assertThat(listUser.getBody()
                        .stream()
                        .map(UserOutListDto::getFirstName)
                        .collect(Collectors.toList())
                , hasItems(first.getFirstName(),second.getFirstName()));

    }

    @Test
    void updateUser() {
        //Given
        User user = UserHelper.createUser();
        user.setId(ConstantValue.ID);

        Document document = new Document();
        document.setDocDate(LocalDate.now());
        document.setDocNumber("123456");
        document.setUser(user);
        document.setDocType(UserHelper.createDocumentTypeForTest());
        document.setId(ConstantValue.ID);
        user.setDocument(document);

        Country countryForTestUser = UserHelper.createCountryForTestUser();
        user.setCountry(countryForTestUser);

        Office office = OfficeHelper.createOffice();
        office.setId(ConstantValue.ID);

        UpdateUserInDto updateUserInDto = UserHelper.createUpdateUserInDto();
        when(userDao.getUserById(ConstantValue.ID)).thenReturn(user);
        when(countryDao.getCountryByCode(countryForTestUser.getCode())).thenReturn(countryForTestUser);
        when(officeService.getOffice(updateUserInDto.getOfficeId())).thenReturn(office);

        //When
        ResponseEntity<SuccessDto> successDtoResponseEntity = userService.updateUser(updateUserInDto);

        //Then
        verify(userDao).updateUser(user);
        verify(userDao).getUserById(ConstantValue.ID);
        verify(countryDao).getCountryByCode(countryForTestUser.getCode());
        verify(officeService).getOffice(updateUserInDto.getOfficeId());
        assertEquals(ConstantValue.RESULT, Objects.requireNonNull(successDtoResponseEntity.getBody()).getResult());
    }
}
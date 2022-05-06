package com.bell.bellschooll.service;

import com.bell.bellschooll.util.ConstantValue;
import com.bell.bellschooll.util.OfficeHelper;
import com.bell.bellschooll.util.OrganizationHelper;
import com.bell.bellschooll.util.UserHelper;
import com.bell.bellschooll.repository.CountryRepository;
import com.bell.bellschooll.repository.DocumentTypeRepository;
import com.bell.bellschooll.dto.request.UpdateUserInDto;
import com.bell.bellschooll.dto.request.UserInListDto;
import com.bell.bellschooll.dto.request.UserInSaveDto;
import com.bell.bellschooll.dto.response.SuccessDto;
import com.bell.bellschooll.dto.response.UserOutDto;
import com.bell.bellschooll.dto.response.UserOutListDto;
import com.bell.bellschooll.exception.anyUserErrorException;
import com.bell.bellschooll.mapper.DocumentMapper;
import com.bell.bellschooll.mapper.UserMapper;
import com.bell.bellschooll.model.Country;
import com.bell.bellschooll.model.Document;
import com.bell.bellschooll.model.DocumentType;
import com.bell.bellschooll.model.Office;
import com.bell.bellschooll.model.Organization;
import com.bell.bellschooll.model.User;
import com.bell.bellschooll.repository.UserRepository;
import com.bell.bellschooll.specification.UserSpecification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test2")
class UserServiceImplTest {

    @Autowired
    UserService userService;

    @MockBean
    UserRepository userRepository;

    @MockBean
    UserSpecification userSpecification;

    @Autowired
    UserMapper userMapper;

    @Autowired
    DocumentMapper documentMapper;

    @MockBean
    OfficeService officeService;

    @MockBean
    CountryRepository countryRepository;

    @MockBean
    DocumentTypeRepository documentTypeRepository;

    @Test
    void addUser() {
        //Given
        UserInSaveDto userInSaveDto = UserHelper.createUserInSaveDto();
        DocumentType documentType = UserHelper.createDocumentTypeForTest();
        Country country = UserHelper.createCountryForTestUser();
        Optional<Country> optionalCountry = Optional.of(country);
        Office office = OfficeHelper.createOffice();

        User user = userMapper.dtoToDomain(userInSaveDto);
        user.setOffice(office);
        user.setCountry(country);

        Document document = documentMapper.dtoToDomain(userInSaveDto);
        document.setDocType(documentType);
        document.setUser(user);

        user.setDocument(document);

        when(countryRepository.getCountryByCode(country.getCode())).thenReturn(optionalCountry);
        when(documentTypeRepository.getDocumentTypeByCode(userInSaveDto.getDocCode())).thenReturn(Optional.of(documentType));
        when(officeService.getOffice(userInSaveDto.getOfficeId())).thenReturn(office);
        when(userRepository.save(user)).thenReturn(user);

        //When
        ResponseEntity<SuccessDto> successDtoResponseEntity = userService.addUser(userInSaveDto);

        //Then
        verify(userRepository).save(user);
        verify(countryRepository).getCountryByCode(country.getCode());
        verify(documentTypeRepository).getDocumentTypeByCode(userInSaveDto.getDocCode());
        verify(officeService).getOffice(userInSaveDto.getOfficeId());
        assertEquals(ConstantValue.RESULT, successDtoResponseEntity.getBody().getResult());
    }

    @Test
    @DisplayName("Метод проверяет работу метода сервиса addUser с неправильным кодом документа")
    void addUserFailDocumentType() {
        //Given
        String failDocCode = "1005";
        UserInSaveDto userInSaveDto = UserHelper.createUserInSaveDto();
        userInSaveDto.setDocCode(failDocCode);
        when(documentTypeRepository.getDocumentTypeByCode(failDocCode)).thenThrow(new anyUserErrorException("test"));
        //When
        assertThrows(anyUserErrorException.class, () -> userService.addUser(userInSaveDto));
        //Then
        verify(documentTypeRepository).getDocumentTypeByCode(failDocCode);
    }

    @Test
    @DisplayName("Метод проверяет работу метода сервиса addUser() с неправильным кодом страны ")
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

        when(documentTypeRepository.getDocumentTypeByCode(userInSaveDto.getDocCode())).thenReturn(Optional.of(documentType));
        when(officeService.getOffice(userInSaveDto.getOfficeId())).thenReturn(office);

        String failCountryCode = "1005";
        userInSaveDto.setCountryCode(failCountryCode);

        when(countryRepository.getCountryByCode(failCountryCode)).thenThrow(new anyUserErrorException("test"));
        //Then
        assertThrows(anyUserErrorException.class, () -> userService.addUser(userInSaveDto));
        verify(documentTypeRepository).getDocumentTypeByCode(userInSaveDto.getDocCode());
        verify(officeService).getOffice(userInSaveDto.getOfficeId());
    }

    @Test
    @DisplayName("Метод проверяет работу метода getUser()")
    void getUser() {
        User user = UserHelper.createUser();
        user.setId(ConstantValue.ID);
        //Given
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        //When
        ResponseEntity<UserOutDto> userServiceResponse = userService.getUser(ConstantValue.ID);

        //Then
        verify(userRepository).findById(ConstantValue.ID);
        UserOutDto userOutDto = userServiceResponse.getBody();
        assertNotNull(userServiceResponse);
        assertEquals(ConstantValue.ID, userOutDto.getId());
        assertEquals(user.getFirstName(), userOutDto.getFirstName());
        assertEquals(user.getSecondName(), userOutDto.getSecondName());
        assertEquals(user.getMiddleName(), userOutDto.getMiddleName());
    }

    @Test
    @DisplayName("Метод проверяет работу метода getUser() с неправильным ID=10005")
    void getUserFail() {
        //Given
        int failIdUser = 10005;
        when(userRepository.findById(failIdUser)).thenReturn(Optional.empty());
        //Then
        assertThrows(anyUserErrorException.class, /*When*/() -> userService.getUser(failIdUser));
        verify(userRepository).findById(failIdUser);
    }

    @Test
    @DisplayName("Метод проверяет работу метода getListUser() ")
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
        when(userRepository.findAll(userSpecification.getUserSpecification(userInListDto, office))).thenReturn(users);

        //When
        ResponseEntity<List<UserOutListDto>> listUser = userService.getListUser(userInListDto);

        //Then
        verify(userRepository).findAll(userSpecification.getUserSpecification(userInListDto, office));
        verify(officeService).getOffice(ConstantValue.ID);
        assertNotNull(listUser);
        assertEquals(listUser.getBody().size(), users.size());
        assertThat(listUser.getBody()
                        .stream()
                        .map(UserOutListDto::getFirstName)
                        .collect(Collectors.toList())
                , hasItems(first.getFirstName(), second.getFirstName()));

    }

    @Test
    @DisplayName("Метод проверяет метод updateUser()")
    void updateUser() {
        //Given
        User user = UserHelper.createUser();
        user.setId(ConstantValue.ID);

        Document document = new Document();
        document.setDate(LocalDate.now());
        document.setNumber("123456");
        document.setUser(user);
        document.setDocType(UserHelper.createDocumentTypeForTest());
        document.setId(ConstantValue.ID);
        user.setDocument(document);

        Country countryForTestUser = UserHelper.createCountryForTestUser();
        user.setCountry(countryForTestUser);

        Office office = OfficeHelper.createOffice();
        office.setId(ConstantValue.ID);

        UpdateUserInDto updateUserInDto = UserHelper.createUpdateUserInDto();
        when(userRepository.findById(ConstantValue.ID)).thenReturn(Optional.of(user));
        when(countryRepository.getCountryByCode(countryForTestUser.getCode())).thenReturn(Optional.of(countryForTestUser));
        when(officeService.getOffice(updateUserInDto.getOfficeId())).thenReturn(office);

        //When
        ResponseEntity<SuccessDto> successDtoResponseEntity = userService.updateUser(updateUserInDto);

        //Then
        verify(userRepository).save(user);
        verify(userRepository).findById(ConstantValue.ID);
        // verify(countryRepository).getCountryByCode(countryForTestUser.getCode());
        verify(officeService).getOffice(updateUserInDto.getOfficeId());
        assertEquals(ConstantValue.RESULT, Objects.requireNonNull(successDtoResponseEntity.getBody()).getResult());
    }
}
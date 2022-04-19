package com.bell.bellschooll.util;

import com.bell.bellschooll.dto.request.UpdateUserInDto;
import com.bell.bellschooll.dto.request.UserInListDto;
import com.bell.bellschooll.dto.request.UserInSaveDto;
import com.bell.bellschooll.model.Country;
import com.bell.bellschooll.model.DocumentType;
import com.bell.bellschooll.model.User;

import java.time.LocalDate;

public class UserHelper {

    public static UserInSaveDto createUserInSaveDto() {
        UserInSaveDto userInSaveDto = new UserInSaveDto();
        userInSaveDto.setFirstName("Фамилия");
        userInSaveDto.setSecondName("Имя");
        userInSaveDto.setMiddleName("Отчество");
        userInSaveDto.setPosition("должность");
        userInSaveDto.setCountryCode(643);
        userInSaveDto.setDocDate(LocalDate.now());
        userInSaveDto.setDocCode("21");
        userInSaveDto.setDocNumber("123456");
        userInSaveDto.setIsIdentified(true);
        userInSaveDto.setOfficeId(ConstantValue.ID);
        userInSaveDto.setPhone("2-57-05");
        return userInSaveDto;
    }

    public static UserInSaveDto createUserInSaveDto(int countryCode) {
        UserInSaveDto userInSaveDto = createUserInSaveDto();
        userInSaveDto.setCountryCode(countryCode);
        return userInSaveDto;
    }

    public static UserInSaveDto createUserInSaveDto(String docCode) {
        UserInSaveDto userInSaveDto = createUserInSaveDto();
        userInSaveDto.setDocCode(docCode);
        return userInSaveDto;
    }

    public static UpdateUserInDto createUpdateUserInDto() {
        UpdateUserInDto updateUserInDto = new UpdateUserInDto();
        updateUserInDto.setId(ConstantValue.ID);
        updateUserInDto.setOfficeId(ConstantValue.ID);
        updateUserInDto.setFirstName("фамилия");
        updateUserInDto.setSecondName("Имя");
        updateUserInDto.setMiddleName("Отчество");
        updateUserInDto.setPosition("должность");
        updateUserInDto.setCitizenshipCode(643);
        updateUserInDto.setDocDate(LocalDate.now());
        updateUserInDto.setDocNumber("123456");
        updateUserInDto.setIsIdentified(true);
        updateUserInDto.setPhone("2-57-05");
        return updateUserInDto;
    }

    public static UserInListDto createUserInListDto() {
        UserInListDto userInListDto = new UserInListDto();
        userInListDto.setOfficeId(ConstantValue.ID);
        userInListDto.setCitizenshipCode("643");
        return userInListDto;
    }

    public static User createUser() {
        User user = new User();
        user.setFirstName("firstname");
        user.setMiddleName("middlename");
        user.setSecondName("secondname");
        user.setPosition("position");
        user.setPhone("2-57-05");
        return user;
    }

    public static DocumentType createDocumentTypeForTest() {
        DocumentType documentType = new DocumentType();
        documentType.setId(1);
        documentType.setName("тип документа");
        documentType.setCode("21");
        documentType.setVersion(1);
        return documentType;
    }

    public static Country createCountryForTestUser() {
        Country country = new Country();
        country.setCode(643);
        country.setName("Страна");
        country.setId(1);
        return country;
    }

}

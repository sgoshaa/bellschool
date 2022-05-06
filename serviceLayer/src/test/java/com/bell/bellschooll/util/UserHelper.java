package com.bell.bellschooll.util;

import com.bell.bellschooll.dto.request.UpdateUserInDto;
import com.bell.bellschooll.dto.request.UserInListDto;
import com.bell.bellschooll.dto.request.UserInSaveDto;
import com.bell.bellschooll.dto.response.UserOutDto;
import com.bell.bellschooll.dto.response.UserOutListDto;
import com.bell.bellschooll.model.Country;
import com.bell.bellschooll.model.DocumentType;
import com.bell.bellschooll.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserHelper {

    public static final String FIRST_NAME = "Фамилия";
    public static final String SECOND_NAME = "Имя";
    public static final String UPDATED_MIDDLE_NAME = "UPDATED_MIDDLE_NAME";
    public static final String POSITION = "должность";
    public static final String COUNTRY_CODE = "622";
    public static final String DOC_CODE = "21";
    public static final String DOC_NUMBER = "1111 1111";
    public static final String PHONE = "2-57-05";
    public static final String UPDATED_FIRST_NAME = "UPDATED_FIRST_NAME";
    public static final String UPDATED_SECOND_NAME = "UPDATED_SECOND_NAME";
    public static final String UPDATED_POSITION = "Обновленная должность";
    public static final String UPDATED_DOC_NUMBER = "123456";
    public static final String COUNTRY_NAME = "Страна";
    public static final String MIDDLE_NAME = "middlename";

    public static UserInSaveDto createUserInSaveDto() {
        UserInSaveDto userInSaveDto = new UserInSaveDto();
        userInSaveDto.setFirstName(FIRST_NAME);
        userInSaveDto.setSecondName(SECOND_NAME);
        userInSaveDto.setMiddleName(MIDDLE_NAME);
        userInSaveDto.setPosition(POSITION);
        userInSaveDto.setCountryCode(COUNTRY_CODE);
        userInSaveDto.setDocDate(LocalDate.now());
        userInSaveDto.setDocCode(DOC_CODE);
        userInSaveDto.setDocNumber(DOC_NUMBER);
        userInSaveDto.setIsIdentified(true);
        userInSaveDto.setOfficeId(ConstantValue.ID);
        userInSaveDto.setPhone(PHONE);
        return userInSaveDto;
    }

    public static UpdateUserInDto createUpdateUserInDto() {
        UpdateUserInDto updateUserInDto = new UpdateUserInDto();
        updateUserInDto.setId(ConstantValue.ID);
        updateUserInDto.setOfficeId(ConstantValue.ID);
        updateUserInDto.setFirstName(UPDATED_FIRST_NAME);
        updateUserInDto.setSecondName(UPDATED_SECOND_NAME);
        updateUserInDto.setMiddleName(UPDATED_MIDDLE_NAME);
        updateUserInDto.setPosition(UPDATED_POSITION);
        updateUserInDto.setCitizenshipCode(COUNTRY_CODE);
        updateUserInDto.setDocDate(LocalDate.now());
        updateUserInDto.setDocNumber(UPDATED_DOC_NUMBER);
        updateUserInDto.setIsIdentified(true);
        updateUserInDto.setPhone(PHONE);
        return updateUserInDto;
    }

    public static UserInListDto createUserInListDto() {
        UserInListDto userInListDto = new UserInListDto();
        userInListDto.setOfficeId(ConstantValue.ID);
        userInListDto.setCitizenshipCode(COUNTRY_CODE);
        return userInListDto;
    }

    public static User createUser() {
        User user = new User();
        user.setFirstName(FIRST_NAME);
        user.setMiddleName(MIDDLE_NAME);
        user.setSecondName(SECOND_NAME);
        user.setPosition(POSITION);
        user.setPhone(PHONE);
        user.setIsIdentified(true);
        return user;
    }

    public static DocumentType createDocumentTypeForTest() {
        DocumentType documentType = new DocumentType();
        documentType.setId(ConstantValue.ID);
        documentType.setName("тип документа");
        documentType.setCode(DOC_CODE);
        documentType.setVersion(1);
        return documentType;
    }

    public static Country createCountryForTestUser() {
        Country country = new Country();
        country.setCode(COUNTRY_CODE);
        country.setName(COUNTRY_NAME);
        country.setId(ConstantValue.ID);
        return country;
    }

    public static UserOutDto createUserOutDto() {
        UserOutDto userOutDto = new UserOutDto();
        userOutDto.setId(ConstantValue.ID);
        userOutDto.setDocDate(LocalDate.now());
        userOutDto.setCitizenshipCode(COUNTRY_CODE);
        userOutDto.setFirstName(FIRST_NAME);
        userOutDto.setIsIdentified(true);
        userOutDto.setPhone(PHONE);
        userOutDto.setPosition(POSITION);
        userOutDto.setDocNumber(DOC_NUMBER);
        userOutDto.setSecondName(SECOND_NAME);
        userOutDto.setMiddleName(MIDDLE_NAME);
        return userOutDto;
    }

    public static List<UserOutListDto> createListUserOutDto() {
        ArrayList<UserOutListDto> userOutList = new ArrayList<>();
        UserOutListDto userOutListDto = new UserOutListDto();
        userOutListDto.setFirstName(FIRST_NAME);
        userOutListDto.setId(ConstantValue.ID);
        userOutListDto.setPosition(POSITION);
        userOutListDto.setMiddleName(MIDDLE_NAME);
        userOutListDto.setSecondName(SECOND_NAME);
        userOutList.add(userOutListDto);
        return userOutList;
    }
}

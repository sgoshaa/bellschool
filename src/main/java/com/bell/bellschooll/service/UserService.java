package com.bell.bellschooll.service;

import com.bell.bellschooll.dao.CountryDao;
import com.bell.bellschooll.dao.DocumentDao;
import com.bell.bellschooll.dao.DocumentTypeDao;
import com.bell.bellschooll.dao.OfficeDao;
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
import com.bell.bellschooll.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserDao userDao;
    private final DocumentDao documentDao;
    private final UserMapper userMapper;
    private final DocumentMapper documentMapper;
    private final OfficeDao officeDao;
    private final CountryDao countryDao;
    private final DocumentTypeDao documentTypeDao;

    public UserService(UserDao userDao, DocumentDao documentDao, UserMapper userMapper, DocumentMapper documentMapper, OfficeDao officeDao, CountryDao countryDao, DocumentTypeDao documentTypeDao) {
        this.userDao = userDao;
        this.documentDao = documentDao;
        this.userMapper = userMapper;
        this.documentMapper = documentMapper;
        this.officeDao = officeDao;
        this.countryDao = countryDao;
        this.documentTypeDao = documentTypeDao;
    }

    @Transactional
    public SuccessDto addUser(UserInSaveDto userInSaveDto) {
        Office office = getOffice(userInSaveDto.getOfficeId());
        User user = userMapper.dtoToDomain(userInSaveDto);
        user.setOffice(office);
//        boolean flag = true;
//        if (flag){
//            throw new RuntimeException("Привет");
//        }
        if (!userInSaveDto.getDocCode().isEmpty() &&
                !userInSaveDto.getDocCode().isBlank() && userInSaveDto.getDocCode() != null) {
            Document document = createDocument(userInSaveDto);
            document.setUser(user);
            user.setDocument(document);
        }
        if (userInSaveDto.getCountryCode() != null) {
            user.setCountry(getCountry(userInSaveDto.getCountryCode()));
        }
        userDao.addUser(user);
        return new SuccessDto();
    }

    private Document createDocument(UserInSaveDto userInSaveDto) {
        DocumentType documentType = documentTypeDao.getDocumentTypeByCode(userInSaveDto.getDocCode());
        if (documentType == null) {
            throw new ErrorException("Не найден нужный тип документа.");
        }
        Document document = documentMapper.dtoToDomain(userInSaveDto);
        document.setDocType(documentType);
        return document;
    }

    private Country getCountry(Integer code) {
        Country country = countryDao.getCountryByCode(code);
        if (country == null) {
            throw new ErrorException("Не найдена страна по данному коду.");
        }
        return country;
    }

    public UserOutDto getUser(Integer id)  {
        User user = getUserById(id);
        UserOutDto userOutDto = userMapper.domainToDto(user);
        return userOutDto;
    }

    private User getUserById(Integer id) {
        User user = userDao.getUserById(id);
        boolean flag=true;
        if (flag){
            throw  new RuntimeException("привет");
        }
        if (user == null) {
            throw new ErrorException("Пользователь с данным id = " + id + " не найден.");
        }
        return user;
    }

    public List<UserOutListDto> getListUser(UserInListDto userInListDto) {
        Office office = getOffice(userInListDto.getOfficeId());
        List<User> users = userDao.getListUser(userInListDto, office);
        List<UserOutListDto> userOutListDtos = new ArrayList<>();
        users.forEach(user -> userOutListDtos.add(userMapper.domainToOutListDto(user)));
        return userOutListDtos;
    }

    @Transactional
    public SuccessDto updateUser(UpdateUserInDto updateUserInDto) {
        User user = getUserById(updateUserInDto.getId());
        User currentUser = userMapper.updateUserFromDto(updateUserInDto, user);
        currentUser.setDocument(documentMapper.updateDocument(updateUserInDto, currentUser.getDocument()));
        if (updateUserInDto.getCitizenshipCode() != null) {
            currentUser.setCountry(getCountry(updateUserInDto.getCitizenshipCode()));
        }
        if (updateUserInDto.getOfficeId() != null) {
            currentUser.setOffice(getOffice(updateUserInDto.getOfficeId()));
        }
        userDao.updateUser(currentUser);
        return new SuccessDto();
    }

    private Office getOffice(Integer officeId) {
        Office office = officeDao.getOfficeById(officeId);
        if (office == null && officeId != 0) {
            throw new ErrorException("Не найден офис c таким id = " + officeId);
        }
        return office;
    }
}

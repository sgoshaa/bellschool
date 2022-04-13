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

/**
 * Сервис для User
 */
@Service
public class UserService {
    private final UserDao userDao;
    private final UserMapper userMapper;
    private final DocumentMapper documentMapper;
    private final OfficeService officeService;
    private final CountryDao countryDao;
    private final DocumentTypeDao documentTypeDao;

    public UserService(UserDao userDao, UserMapper userMapper, DocumentMapper documentMapper, OfficeService officeService, CountryDao countryDao, DocumentTypeDao documentTypeDao) {
        this.userDao = userDao;
        this.userMapper = userMapper;
        this.documentMapper = documentMapper;
        this.officeService = officeService;
        this.countryDao = countryDao;
        this.documentTypeDao = documentTypeDao;
    }

    /**
     * Метод для сохранения нового пользователя
     *
     * @param userInSaveDto Объект, содержащий параметры нового пользователя
     * @return SuccessDto
     */
    @Transactional
    public ResponseEntity<SuccessDto> addUser(UserInSaveDto userInSaveDto) {
        Office office = officeService.getOffice(userInSaveDto.getOfficeId());
        User user = userMapper.dtoToDomain(userInSaveDto);
        user.setOffice(office);
        if (userInSaveDto.getDocCode() != null &&
                !userInSaveDto.getDocCode().isBlank() && !userInSaveDto.getDocCode().isEmpty()) {
            Document document = createDocument(userInSaveDto);
            document.setUser(user);
            user.setDocument(document);
        }
        if (userInSaveDto.getCountryCode() != null) {
            user.setCountry(getCountry(userInSaveDto.getCountryCode()));
        }
        userDao.addUser(user);
        return new ResponseEntity<>(new SuccessDto(), HttpStatus.OK);
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

    /**
     * Метод для получения пользователя по id
     *
     * @param id Уникальный идентификатор пользователя
     * @return UserOutDto
     */
    public ResponseEntity<UserOutDto> getUser(Integer id) {
        User user = getUserById(id);
        UserOutDto userOutDto = userMapper.domainToDto(user);
        return new ResponseEntity<>(userOutDto, HttpStatus.OK);
    }

    private User getUserById(Integer id) {
        User user = userDao.getUserById(id);
        if (user == null) {
            throw new ErrorException("Пользователь с данным id = " + id + " не найден.");
        }
        return user;
    }

    /**
     * Метод для получения списка пользователей по фильтру
     *
     * @param userInListDto Объект, содержащий параметры, для фильтрации пользователя
     * @return List Список объектов UserOutListDto
     */
    public ResponseEntity<List<UserOutListDto>> getListUser(UserInListDto userInListDto) {
        Office office = officeService.getOffice(userInListDto.getOfficeId());
        List<User> users = userDao.getListUser(userInListDto, office);
        List<UserOutListDto> userOutListDtos = new ArrayList<>();
        users.forEach(user -> userOutListDtos.add(userMapper.domainToOutListDto(user)));
        return new ResponseEntity<>(userOutListDtos, HttpStatus.OK);
    }

    /**
     * Метод для обновление пользователя
     *
     * @param updateUserInDto Объект, содержащий параметры,для обновления пользователя
     * @return SuccessDto
     */
    @Transactional
    public ResponseEntity<SuccessDto> updateUser(UpdateUserInDto updateUserInDto) {
        User user = getUserById(updateUserInDto.getId());
        User currentUser = userMapper.updateUserFromDto(updateUserInDto, user);
        currentUser.setDocument(documentMapper.updateDocument(updateUserInDto, currentUser.getDocument()));
        if (updateUserInDto.getCitizenshipCode() != null) {
            currentUser.setCountry(getCountry(updateUserInDto.getCitizenshipCode()));
        }
        if (updateUserInDto.getOfficeId() != null) {
            currentUser.setOffice(officeService.getOffice(updateUserInDto.getOfficeId()));
        }
        userDao.updateUser(currentUser);
        return new ResponseEntity<>(new SuccessDto(), HttpStatus.OK);
    }
}

package com.bell.bellschooll.service;

import com.bell.bellschooll.dao.CountryDao;
import com.bell.bellschooll.dao.DocumentTypeDao;
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
import com.bell.bellschooll.repository.UserRepository;
import com.bell.bellschooll.repository.specification.UserSpecification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Сервис для User
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final DocumentMapper documentMapper;
    private final OfficeService officeService;
    private final CountryDao countryDao;
    private final DocumentTypeDao documentTypeDao;
    private final UserSpecification userSpecification;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, DocumentMapper documentMapper, OfficeService officeService, CountryDao countryDao, DocumentTypeDao documentTypeDao, UserSpecification userSpecification) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.documentMapper = documentMapper;
        this.officeService = officeService;
        this.countryDao = countryDao;
        this.documentTypeDao = documentTypeDao;
        this.userSpecification = userSpecification;
    }

    /**
     * Метод для сохранения нового пользователя
     *
     * @param userInSaveDto Объект, содержащий параметры нового пользователя
     * @return SuccessDto
     */
    @Override
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
        userRepository.save(user);
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
    @Override
    public ResponseEntity<UserOutDto> getUser(Integer id) {
        User user = getUserById(id);
        UserOutDto userOutDto = userMapper.domainToDto(user);
        return new ResponseEntity<>(userOutDto, HttpStatus.OK);
    }

    private User getUserById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new ErrorException("Пользователь с данным id = " + id + " не найден.");
        }
        return user.get();
    }

    /**
     * Метод для получения списка пользователей по фильтру
     *
     * @param userInListDto Объект, содержащий параметры, для фильтрации пользователя
     * @return List Список объектов UserOutListDto
     */
    @Override
    public ResponseEntity<List<UserOutListDto>> getListUser(UserInListDto userInListDto) {
        Office office = officeService.getOffice(userInListDto.getOfficeId());
        List<User> users = userRepository.findAll(userSpecification.getUserSpecification(userInListDto, office));
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
    @Override
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
        userRepository.save(currentUser);
        return new ResponseEntity<>(new SuccessDto(), HttpStatus.OK);
    }
}

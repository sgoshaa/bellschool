package com.bell.bellschooll.service;

import com.bell.bellschooll.dto.request.UpdateUserInDto;
import com.bell.bellschooll.dto.request.UserInListDto;
import com.bell.bellschooll.dto.request.UserInSaveDto;
import com.bell.bellschooll.exception.anyUserErrorException;
import com.bell.bellschooll.mapper.DocumentMapper;
import com.bell.bellschooll.mapper.UserMapper;
import com.bell.bellschooll.repository.DocumentTypeRepository;
import com.bell.bellschooll.dto.response.SuccessDto;
import com.bell.bellschooll.dto.response.UserOutDto;
import com.bell.bellschooll.dto.response.UserOutListDto;
import com.bell.bellschooll.model.Country;
import com.bell.bellschooll.model.Document;
import com.bell.bellschooll.model.DocumentType;
import com.bell.bellschooll.model.Office;
import com.bell.bellschooll.model.User;
import com.bell.bellschooll.repository.UserRepository;
import com.bell.bellschooll.specification.UserSpecification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Сервис для User
 */
@Service
public class UserServiceImpl implements UserService {

    private final CountryService countryService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final DocumentMapper documentMapper;
    private final OfficeService officeService;
    private final DocumentTypeRepository documentTypeRepository;
    private final UserSpecification userSpecification;

    public UserServiceImpl(CountryService countryService, UserRepository userRepository, UserMapper userMapper, DocumentMapper documentMapper, OfficeService officeService, DocumentTypeRepository documentTypeRepository, UserSpecification userSpecification) {
        this.countryService = countryService;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.documentMapper = documentMapper;
        this.officeService = officeService;
        this.documentTypeRepository = documentTypeRepository;
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
            user.setCountry(countryService.getCountryByCode(userInSaveDto.getCountryCode()));
        }
        userRepository.save(user);
        return new ResponseEntity<>(new SuccessDto(), HttpStatus.OK);
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
        return new ResponseEntity<>(userMapper.toListDto(users), HttpStatus.OK);
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
        if (updateUserInDto.getCitizenshipCode() != null && !updateUserInDto.getCitizenshipCode().equals(currentUser.getCountry().getCode())){
            Country country = countryService.getCountryByCode(updateUserInDto.getCitizenshipCode());
            currentUser.setCountry(country);
        }
        if (updateUserInDto.getOfficeId() != null) {
            currentUser.setOffice(officeService.getOffice(updateUserInDto.getOfficeId()));
        }
        currentUser.setDocument(documentMapper.dtoToDomain(updateUserInDto, currentUser.getDocument()));
        userRepository.save(currentUser);
        return new ResponseEntity<>(new SuccessDto(), HttpStatus.OK);
    }

    /**
     * Метод для создания нового документа
     *
     * @param userInSaveDto объект с параеметрами нового документа
     * @return Document
     */
    private Document createDocument(UserInSaveDto userInSaveDto) {
        DocumentType documentType = documentTypeRepository.getDocumentTypeByCode(userInSaveDto.getDocCode())
                .orElseThrow(() -> new anyUserErrorException("Не найден нужный тип документа."));
        Document document = documentMapper.dtoToDomain(userInSaveDto);
        document.setDocType(documentType);
        return document;
    }

    /**
     * Метод для получения пользователя по его Id
     *
     * @param id Уникальный идентификатор пользователя
     * @return User
     */
    private User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new anyUserErrorException("Пользователь с данным id = " + id + " не найден."));
    }
}

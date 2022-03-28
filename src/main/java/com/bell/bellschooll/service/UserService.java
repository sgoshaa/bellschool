package com.bell.bellschooll.service;

import com.bell.bellschooll.dao.CountryDao;
import com.bell.bellschooll.dao.DocumentDao;
import com.bell.bellschooll.dao.DocumentTypeRepository;
import com.bell.bellschooll.dao.OfficeDao;
import com.bell.bellschooll.dao.UserDao;
import com.bell.bellschooll.dto.request.OfficeInUpdateDto;
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
    private final DocumentTypeRepository documentTypeRepository;

    public UserService(UserDao userDao, DocumentDao documentDao, UserMapper userMapper, DocumentMapper documentMapper, OfficeDao officeDao, CountryDao countryDao, DocumentTypeRepository documentTypeRepository) {
        this.userDao = userDao;
        this.documentDao = documentDao;
        this.userMapper = userMapper;
        this.documentMapper = documentMapper;
        this.officeDao = officeDao;
        this.countryDao = countryDao;
        this.documentTypeRepository = documentTypeRepository;
    }

    @Transactional
    public ResponseEntity<SuccessDto> addUser(UserInSaveDto userInSaveDto) {
        Office office = officeDao.getOfficeById(userInSaveDto.getOfficeId());
        if (office == null) {
            throw new ErrorException("Не найден офис.");
        }
        User user = userMapper.dtoToDomain(userInSaveDto);
        user.setOffice(office);
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
        return new ResponseEntity<>(new SuccessDto(), HttpStatus.OK);
    }

    private Document createDocument(UserInSaveDto userInSaveDto) {
        DocumentType documentType = documentTypeRepository.getDocumentTypeByCode(userInSaveDto.getDocCode());
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

    public ResponseEntity<UserOutDto> getUser(Integer id) {
        User user = userDao.getUserById(id);
        if (user == null) {
            throw new ErrorException("Пользователь с данным id = " + id + " не найден.");
        }
        UserOutDto userOutDto = userMapper.domainToDto(user);
        return new ResponseEntity<>(userOutDto, HttpStatus.OK);
    }

    public List<UserOutListDto> getListUser(UserInListDto userInListDto) {
        Office office = officeDao.getOfficeById(userInListDto.getOfficeId());
        if (office == null) {
            throw new ErrorException("Не найден офис с данным id = " + userInListDto.getOfficeId());
        }

        List<User> users = userDao.getListUser(userInListDto, office);
        List<UserOutListDto> userOutListDtos = new ArrayList<>();
        users.forEach(user -> userOutListDtos.add(userMapper.domainToOutListDto(user)));
        return userOutListDtos;
    }
}

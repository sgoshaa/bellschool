package com.bell.bellschooll.service;

import com.bell.bellschooll.dao.CountryDao;
import com.bell.bellschooll.dao.DocumentDao;
import com.bell.bellschooll.dao.OfficeDao;
import com.bell.bellschooll.dao.UserDao;
import com.bell.bellschooll.dto.request.UserInSaveDto;
import com.bell.bellschooll.dto.response.SuccessDto;
import com.bell.bellschooll.exception.ErrorException;
import com.bell.bellschooll.mapper.DocumentMapper;
import com.bell.bellschooll.mapper.UserMapper;
import com.bell.bellschooll.model.Document;
import com.bell.bellschooll.model.Office;
import com.bell.bellschooll.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDao userDao;
    private final DocumentDao documentDao;
    private final UserMapper userMapper;
    private final DocumentMapper documentMapper;
    private final OfficeDao officeDao;
    private final CountryDao countryDao;

    public UserService(UserDao userDao, DocumentDao documentDao, UserMapper userMapper, DocumentMapper documentMapper, OfficeDao officeDao, CountryDao countryDao) {
        this.userDao = userDao;
        this.documentDao = documentDao;
        this.userMapper = userMapper;
        this.documentMapper = documentMapper;
        this.officeDao = officeDao;
        this.countryDao = countryDao;
    }

    public ResponseEntity<SuccessDto> addUser(UserInSaveDto userInSaveDto){
        Office office = officeDao.getOfficeById(userInSaveDto.getOfficeId());
        if (office==null){
            throw new ErrorException("Не найден офис.");
        }
        Document document = documentMapper.dtoToDomain(userInSaveDto);
        User user = userMapper.dtoToDomain(userInSaveDto);
        user.setOffice(office);
        user.setDocument(document);
        userDao.addUser(user);
        return new ResponseEntity<>(new SuccessDto(), HttpStatus.OK);
    }
}

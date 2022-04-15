package com.bell.bellschooll.service;

import com.bell.bellschooll.dto.request.UpdateUserInDto;
import com.bell.bellschooll.dto.request.UserInListDto;
import com.bell.bellschooll.dto.request.UserInSaveDto;
import com.bell.bellschooll.dto.response.SuccessDto;
import com.bell.bellschooll.dto.response.UserOutDto;
import com.bell.bellschooll.dto.response.UserOutListDto;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    @Transactional
    ResponseEntity<SuccessDto> addUser(UserInSaveDto userInSaveDto);

    ResponseEntity<UserOutDto> getUser(Integer id);

    ResponseEntity<List<UserOutListDto>> getListUser(UserInListDto userInListDto);

    @Transactional
    ResponseEntity<SuccessDto> updateUser(UpdateUserInDto updateUserInDto);
}

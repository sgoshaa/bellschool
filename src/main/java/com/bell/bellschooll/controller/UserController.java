package com.bell.bellschooll.controller;

import com.bell.bellschooll.dto.request.UpdateUserInDto;
import com.bell.bellschooll.dto.request.UserInListDto;
import com.bell.bellschooll.dto.request.UserInSaveDto;
import com.bell.bellschooll.dto.response.SuccessDto;
import com.bell.bellschooll.dto.response.UserOutDto;
import com.bell.bellschooll.dto.response.UserOutListDto;
import com.bell.bellschooll.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер для работы с User
 */
@RestController
@RequestMapping("api/user/")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Сохранение нового пользователя
     *
     * @param userInSaveDto
     * @return
     */
    @PostMapping("save")
    public SuccessDto addUser(@Valid @RequestBody UserInSaveDto userInSaveDto) {
        return userService.addUser(userInSaveDto);
    }

    /**
     * Получение пользователя по id
     *
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public UserOutDto getUser(@PathVariable Integer id) {
        return userService.getUser(id);
    }

    /**
     * Получение списка пользователей по фильтру
     *
     * @param userInListDto
     * @return
     */
    @PostMapping("list")
    public List<UserOutListDto> getListUser(@Valid @RequestBody UserInListDto userInListDto) {
        return userService.getListUser(userInListDto);
    }

    /**
     * Обновление пользователя
     *
     * @param updateUserInDto
     * @return
     */
    @PostMapping("update")
    public SuccessDto updateUser(@Valid @RequestBody UpdateUserInDto updateUserInDto) {
        return userService.updateUser(updateUserInDto);
    }
}

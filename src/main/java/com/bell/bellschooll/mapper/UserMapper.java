package com.bell.bellschooll.mapper;

import com.bell.bellschooll.dto.request.UpdateUserInDto;
import com.bell.bellschooll.dto.request.UserInSaveDto;
import com.bell.bellschooll.dto.response.UserOutDto;
import com.bell.bellschooll.dto.response.UserOutListDto;
import com.bell.bellschooll.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * Маппер для User
 */
@Mapper(componentModel = "spring", uses = DocumentMapper.class)
public interface UserMapper {
    /**
     * метод для маппинга из UserInSaveDto в User
     *
     * @param userInSaveDto
     * @return
     */
    User dtoToDomain(UserInSaveDto userInSaveDto);

    /**
     * метод для маппинга из User в UserOutDto
     *
     * @param user
     * @return
     */
    @Mapping(target = "docName", source = "document.docType.name")
    @Mapping(target = "docNumber", source = "document.number")
    @Mapping(target = "docDate", source = "document.date")
    @Mapping(target = "citizenshipName", source = "country.name")
    @Mapping(target = "citizenshipCode", source = "country.code")
    UserOutDto domainToDto(User user);

    /**
     * Метод для маппинга из User в UserOutListDto
     *
     * @param user
     * @return
     */
    UserOutListDto domainToOutListDto(User user);

    /**
     * Метод для маппинга из UpdateUserInDto в User
     *
     * @param updateUserInDto
     * @param user
     * @return
     */
    User updateUserFromDto(UpdateUserInDto updateUserInDto, @MappingTarget User user);

    /**
     * Метод для маппинга из Списка пользователей в список UserOutListDto
     *
     * @param users список пользователей
     * @return List<UserOutListDto>
     */
    List<UserOutListDto> toListDto(List<User> users);
}

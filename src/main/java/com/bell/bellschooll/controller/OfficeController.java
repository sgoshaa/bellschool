package com.bell.bellschooll.controller;

import com.bell.bellschooll.dto.request.OfficeInListDto;
import com.bell.bellschooll.dto.request.OfficeInSaveDto;
import com.bell.bellschooll.dto.request.OfficeInUpdateDto;
import com.bell.bellschooll.dto.response.OfficeListOutDto;
import com.bell.bellschooll.dto.response.OfficeOutDto;
import com.bell.bellschooll.dto.response.SuccessDto;
import com.bell.bellschooll.service.OfficeService;
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
 * Контроллер для работы с Office
 */
@RestController
@RequestMapping("api/office/")
public class OfficeController {

    private final OfficeService officeService;

    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    /**
     * метод для получения офиса по id
     *
     * @param id Уникальный идентификатор офиса
     * @return OfficeOutDto объект ,содержащий представление офиса
     */
    @GetMapping("{id}")
    public ResponseEntity<OfficeOutDto> getById(@PathVariable Integer id) {
        return officeService.getOfficeById(id);
    }

    /**
     * Метод для сохранения нового офиса
     *
     * @param office запрос OfficeInSaveDto,содержащий параметы нового офиса
     * @return SuccessDto
     */
    @PostMapping("save")
    public ResponseEntity<SuccessDto> addOffice(@Valid @RequestBody OfficeInSaveDto office) {
        return officeService.addOffice(office);
    }

    /**
     * Метод для получения списка офиса по фильтру
     *
     * @param office запрос OfficeInListDto, содержащий параметры для фильтрации офисов
     * @return Список офисов
     */
    @PostMapping("list")
    public ResponseEntity<List<OfficeListOutDto>> listOffice(@Valid @RequestBody OfficeInListDto office) {
        return officeService.getListOffice(office);
    }

    /**
     * Обновление офиса
     *
     * @param officeInUpdateDto запрос OfficeInUpdateDto, содержащий параметры для обновления офиса
     * @return SuccessDto
     */
    @PostMapping("update")
    public ResponseEntity<SuccessDto> updateOffice(@Valid @RequestBody OfficeInUpdateDto officeInUpdateDto) {
        return officeService.updateOffice(officeInUpdateDto);
    }
}

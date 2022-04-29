package com.bell.bellschooll.service;

import com.bell.bellschooll.dto.request.OfficeInListDto;
import com.bell.bellschooll.dto.request.OfficeInSaveDto;
import com.bell.bellschooll.dto.request.OfficeInUpdateDto;
import com.bell.bellschooll.dto.response.OfficeListOutDto;
import com.bell.bellschooll.dto.response.OfficeOutDto;
import com.bell.bellschooll.dto.response.SuccessDto;
import com.bell.bellschooll.model.Office;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Интерфейс для работы с Office
 */
public interface OfficeService {
    /**
     * @see OfficeServiceImpl#getOfficeById(Integer)
     */
    ResponseEntity<OfficeOutDto> getOfficeById(Integer id);

    /**
     * @see OfficeServiceImpl#addOffice(OfficeInSaveDto)
     */
    ResponseEntity<SuccessDto> addOffice(OfficeInSaveDto officeDto);

    /**
     * @see OfficeServiceImpl#getListOffice(OfficeInListDto)
     */
    ResponseEntity<List<OfficeListOutDto>> getListOffice(OfficeInListDto officeInListDto);

    /**
     * @see OfficeServiceImpl#updateOffice(OfficeInUpdateDto)
     */
    ResponseEntity<SuccessDto> updateOffice(OfficeInUpdateDto officeInUpdateDto);

    /**
     * Служебный метод который возвращает office по его id
     *
     * @param id Уникальный идентификатор office
     * @return Office
     */
    Office getOffice(Integer id);
}

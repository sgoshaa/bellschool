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

public interface OfficeService {

    ResponseEntity<OfficeOutDto> getOfficeById(Integer id);

    ResponseEntity<SuccessDto> addOffice(OfficeInSaveDto officeDto);

    ResponseEntity<List<OfficeListOutDto>> listOffice(OfficeInListDto officeInListDto);

    ResponseEntity<SuccessDto> updateOffice(OfficeInUpdateDto officeInUpdateDto);

    Office getOffice(Integer id);
}

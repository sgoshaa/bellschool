package com.bell.bellschooll.service;

import com.bell.bellschooll.dao.OfficeDao;
import com.bell.bellschooll.dto.response.OfficeOutDto;
import com.bell.bellschooll.exception.ErrorException;
import com.bell.bellschooll.mapper.OfficeMapper;
import com.bell.bellschooll.model.Office;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OfficeService {
    private final OfficeDao officeDao;
    private final OfficeMapper officeMapper;

    public OfficeService(OfficeDao officeDao, OfficeMapper officeMapper) {
        this.officeDao = officeDao;
        this.officeMapper = officeMapper;
    }

    public ResponseEntity<OfficeOutDto> getOfficeById(Integer id) {
        Office office = officeDao.getOfficeById(id);
        if (office == null) {
            throw new ErrorException("Офис не найден");
        }

        return new ResponseEntity<OfficeOutDto>(officeMapper.officeToDto(office), HttpStatus.OK);
    }
}

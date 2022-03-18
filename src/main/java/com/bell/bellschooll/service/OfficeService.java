package com.bell.bellschooll.service;

import com.bell.bellschooll.dao.OfficeDao;
import com.bell.bellschooll.dao.OrganizationDao;
import com.bell.bellschooll.dto.request.OfficeInSaveDto;
import com.bell.bellschooll.dto.response.OfficeOutDto;
import com.bell.bellschooll.dto.response.SuccessDto;
import com.bell.bellschooll.exception.ErrorException;
import com.bell.bellschooll.mapper.OfficeMapper;
import com.bell.bellschooll.model.Office;
import com.bell.bellschooll.model.Organization;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OfficeService {
    private final OfficeDao officeDao;
    private final OrganizationDao organizationDao;
    private final OfficeMapper officeMapper;

    public OfficeService(OfficeDao officeDao, OrganizationDao organizationDao, OfficeMapper officeMapper) {
        this.officeDao = officeDao;
        this.organizationDao = organizationDao;
        this.officeMapper = officeMapper;
    }

    public ResponseEntity<OfficeOutDto> getOfficeById(Integer id) {
        Office office = officeDao.getOfficeById(id);
        if (office == null) {
            throw new ErrorException("Офис не найден");
        }

        return new ResponseEntity<OfficeOutDto>(officeMapper.officeToDto(office), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<SuccessDto> addOffice(OfficeInSaveDto officeDto) {

        Organization organization = organizationDao.getOrganizationById(officeDto.getOrgId());
        if (organization == null){
            throw new ErrorException("Не найдена организация!");
        }
        Office office = officeMapper.dtoToDomain(officeDto,organization);
        officeDao.addOffice(office);
        return new ResponseEntity<>(new SuccessDto(),HttpStatus.OK);
    }
}

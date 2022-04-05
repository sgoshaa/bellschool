package com.bell.bellschooll.service;

import com.bell.bellschooll.dao.OfficeDao;
import com.bell.bellschooll.dao.OrganizationDao;
import com.bell.bellschooll.dto.request.OfficeInListDto;
import com.bell.bellschooll.dto.request.OfficeInSaveDto;
import com.bell.bellschooll.dto.request.OfficeInUpdateDto;
import com.bell.bellschooll.dto.response.OfficeListOutDto;
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

import java.util.ArrayList;
import java.util.List;

@Service
public class OfficeService {
    private final OfficeDao officeDao;
    private final OfficeMapper officeMapper;
    private final OrganizationService organizationService;

    public OfficeService(OfficeDao officeDao, OfficeMapper officeMapper, OrganizationService organizationService) {
        this.officeDao = officeDao;
        this.officeMapper = officeMapper;
        this.organizationService = organizationService;
    }

    public ResponseEntity<OfficeOutDto> getOfficeById(Integer id) {
        Office office = officeDao.getOfficeById(id);
        if (office == null) {
            throw new ErrorException("Офис не найден");
        }
        return new ResponseEntity<>(officeMapper.officeToDto(office), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<SuccessDto> addOffice(OfficeInSaveDto officeDto) {
        Organization organization = organizationService.getOrgById(officeDto.getOrgId());
        Office office = officeMapper.dtoToDomain(officeDto, organization);
        officeDao.addOffice(office);
        return new ResponseEntity<>(new SuccessDto(), HttpStatus.OK);
    }

    public ResponseEntity<List<OfficeListOutDto>> listOffice(OfficeInListDto officeInListDto) {
        Organization organization = organizationService.getOrgById(officeInListDto.getOrgId());
        List<Office> offices = officeDao.getListOffice(officeInListDto, organization);
        if (offices.isEmpty()) {
            throw new ErrorException("У " + organization.getFullName() + " нет офисов.");
        }
        List<OfficeListOutDto> officeListOutDtos = new ArrayList<>();
        offices.forEach(office -> officeListOutDtos.add(officeMapper.officeToListDto(office)));
        return new ResponseEntity<>(officeListOutDtos, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<SuccessDto> updateOffice(OfficeInUpdateDto officeInUpdateDto) {
        Office office = officeDao.getOfficeById(officeInUpdateDto.getId());
        if (office == null) {
            throw new ErrorException("Офис не найден");
        }
        office = officeMapper.updateOfficeDtoToDomain(officeInUpdateDto, office);
        officeDao.updateOffice(office);
        return new ResponseEntity<>(new SuccessDto(), HttpStatus.OK);
    }
}

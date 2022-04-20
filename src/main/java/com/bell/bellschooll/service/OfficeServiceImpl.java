package com.bell.bellschooll.service;

import com.bell.bellschooll.dao.OfficeDao;
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
import com.bell.bellschooll.repository.OfficeRepository;
import com.bell.bellschooll.repository.specification.OfficeSpecification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Сервис для Office
 */
@Service
public class OfficeServiceImpl implements OfficeService {
    private final OfficeMapper officeMapper;
    private final OrganizationService organizationService;
    private final OfficeRepository officeRepository;
    private final OfficeSpecification officeSpecification;

    public OfficeServiceImpl(OfficeMapper officeMapper, OrganizationService organizationService, OfficeRepository officeRepository, OfficeSpecification officeSpecification) {
        this.officeRepository = officeRepository;
        this.officeMapper = officeMapper;
        this.organizationService = organizationService;
        this.officeSpecification = officeSpecification;
    }

    /**
     * Метод для получения офиса по его id
     *
     * @param id
     * @return
     */
    public ResponseEntity<OfficeOutDto> getOfficeById(Integer id) {
        Office office = getOffice(id);
        return new ResponseEntity<>(officeMapper.officeToDto(office), HttpStatus.OK);
    }

    /**
     * Метод для сохранения нового офиса
     *
     * @param officeDto
     * @return
     * @see OfficeDao#addOffice(Office)
     */
    @Transactional
    public ResponseEntity<SuccessDto> addOffice(OfficeInSaveDto officeDto) {
        Organization organization = organizationService.getOrgById(officeDto.getOrgId());
        Office office = officeMapper.dtoToDomain(officeDto, organization);
        officeRepository.save(office);
        return new ResponseEntity<>(new SuccessDto(), HttpStatus.OK);
    }

    /**
     * Метод для поиска офиса по фильтру
     *
     * @param officeInListDto запрос с параметрами
     * @return список офисов, подходящих под фильтр
     */
    public ResponseEntity<List<OfficeListOutDto>> getListOffice(OfficeInListDto officeInListDto) {
        Organization organization = organizationService.getOrgById(officeInListDto.getOrgId());
        List<Office> offices = officeRepository.findAll(officeSpecification.getSpecification(officeInListDto, organization));
        if (offices.isEmpty()) {
            throw new ErrorException("У " + organization.getFullName() + " нет офисов.");
        }
        List<OfficeListOutDto> officeListOutDtos = new ArrayList<>();
        offices.forEach(office -> officeListOutDtos.add(officeMapper.officeToListDto(office)));
        return new ResponseEntity<>(officeListOutDtos, HttpStatus.OK);
    }

    /**
     * Метод для обновления офиса
     *
     * @param officeInUpdateDto запрос с обновленными полями
     * @return SuccessDto
     */
    @Transactional
    public ResponseEntity<SuccessDto> updateOffice(OfficeInUpdateDto officeInUpdateDto) {
        Office office = getOffice(officeInUpdateDto.getId());
        office = officeMapper.updateOfficeDtoToDomain(officeInUpdateDto, office);
        officeRepository.save(office);
        return new ResponseEntity<>(new SuccessDto(), HttpStatus.OK);
    }

    /**
     * Метод для получения офиса по его Id
     *
     * @param id Уникальный идентификатор пользователя
     * @return Office
     */
    public Office getOffice(Integer id) {
        Optional<Office> office = officeRepository.findById(id);
        if (office.isEmpty()) {
            throw new ErrorException("Офис не найден c таким id= "+id);
        }
        return office.get();
    }
}

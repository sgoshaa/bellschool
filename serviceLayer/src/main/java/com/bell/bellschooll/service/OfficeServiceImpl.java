package com.bell.bellschooll.service;

import com.bell.bellschooll.dto.request.OfficeInListDto;
import com.bell.bellschooll.dto.request.OfficeInSaveDto;
import com.bell.bellschooll.dto.request.OfficeInUpdateDto;
import com.bell.bellschooll.mapper.OfficeMapper;
import com.bell.bellschooll.dto.response.OfficeListOutDto;
import com.bell.bellschooll.dto.response.OfficeOutDto;
import com.bell.bellschooll.dto.response.SuccessDto;
import com.bell.bellschooll.exception.anyUserErrorException;
import com.bell.bellschooll.model.Office;
import com.bell.bellschooll.model.Organization;
import com.bell.bellschooll.repository.OfficeRepository;
import com.bell.bellschooll.specification.OfficeSpecification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Сервис для Office
 */
@Service
public class OfficeServiceImpl implements OfficeService {

    public static final String OFFICE_NOT_FOUND = "Офис не найден c таким id= ";
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
     * @param id уникальный идентификатор офиса
     * @return ResponseEntity<OfficeOutDto>
     */
    public ResponseEntity<OfficeOutDto> getOfficeById(Integer id) {
        Office office = getOffice(id);
        return new ResponseEntity<>(officeMapper.officeToDto(office), HttpStatus.OK);
    }

    /**
     * Метод для сохранения нового офиса
     *
     * @param officeDto объект с параметрами для сохранения нового офиса
     * @return ResponseEntity<SuccessDto>
     * @see OfficeServiceImpl#addOffice(OfficeInSaveDto)
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
        List<Office> offices = officeRepository.findAll(officeSpecification
                .getSpecification(officeInListDto, organization));
//        List<Office> offices = officeRepository
//                .findAll(officeSpecification.getSpecification(
//                        officeMapper.fromDtoToMap(officeInListDto), organization.getId()));

        if (offices.isEmpty()) {
            throw new anyUserErrorException("У " + organization.getFullName() + " нет офисов.");
        }
        return new ResponseEntity<>(officeMapper.toListDto(offices), HttpStatus.OK);
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
        return officeRepository.findById(id).orElseThrow(() -> new anyUserErrorException(OFFICE_NOT_FOUND + id));
    }
}

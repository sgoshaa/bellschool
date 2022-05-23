package com.bell.bellschooll.service;

import com.bell.bellschooll.config.RabbitMQConfig;
import com.bell.bellschooll.dto.request.OrganisationDtoRequest;
import com.bell.bellschooll.dto.request.OrganizationSaveInDto;
import com.bell.bellschooll.dto.request.OrganizationUpdateInDto;
import com.bell.bellschooll.dto.response.OrganizationListOut;
import com.bell.bellschooll.dto.response.OrganizationOutDto;
import com.bell.bellschooll.dto.response.SuccessDto;
import com.bell.bellschooll.exception.anyUserErrorException;
import com.bell.bellschooll.mapper.OrganizationMapper;
import com.bell.bellschooll.model.Organization;
import com.bell.bellschooll.repository.OrganizationRepository;
import com.bell.bellschooll.specification.OrganizationSpecification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Сервис для Organization
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

    public static final String ORGANIZATION_NOT_FOUND = "Не найдена организация с id = ";
    private final OrganizationMapper organizationMapper;
    private final OrganizationRepository organizationRepository;
    private final OrganizationSpecification organizationSpecification;

    private final RabbitTemplate rabbitTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    public OrganizationServiceImpl(OrganizationMapper organizationMapper, OrganizationRepository organizationRepository, OrganizationSpecification organizationSpecification, RabbitTemplate rabbitTemplate) {
        this.organizationMapper = organizationMapper;
        this.organizationRepository = organizationRepository;
        this.organizationSpecification = organizationSpecification;
        this.rabbitTemplate = rabbitTemplate;
    }


    /**
     * Метод для получения организации по id
     *
     * @param id Уникальный идентификатор организации
     * @return OrganizationOutDto
     */
    @Override
    public ResponseEntity<OrganizationOutDto> getOrganizationById(Integer id) {
        Organization organization = getOrgById(id);
        OrganizationOutDto organizationOutDto = organizationMapper.organizationToDto(organization);
        return new ResponseEntity<>(organizationOutDto, HttpStatus.OK);
    }

    /**
     * Метод для получения организации по имени
     *
     * @param organisationDTO Объект,содержащий параметры, для фильтрации организаций
     * @return List Список объектов типа OrganizationListOut
     */
    @Override
    public ResponseEntity<List<OrganizationListOut>> getOrganizationByOrganizationDtoRequest(
            OrganisationDtoRequest organisationDTO) {
        List<Organization> organizationList = organizationRepository.findAll(organizationSpecification
                .getSpecification(organisationDTO));
        return new ResponseEntity<>(organizationMapper.toListDto(organizationList), HttpStatus.OK);
    }

    /**
     * Метод для сохранения новой организации
     *
     * @param organizationSaveInDto Объект, содержащий параметры для новой организации
     * @return SuccessDto
     */
    @Override
    @Transactional
    public ResponseEntity<SuccessDto> addOrganization(OrganizationSaveInDto organizationSaveInDto) {
        Organization organization = organizationMapper.organizationInToDomain(organizationSaveInDto);
        organizationRepository.save(organization);
        return new ResponseEntity<>(new SuccessDto(), HttpStatus.OK);
    }

    /**
     * Метод для обновления организации
     *
     * @param organizationUpdateInDto Объект, содержащий параметры для обновления организаци
     * @return SuccessDto
     */
    @Override
    @Transactional
    public ResponseEntity<SuccessDto> updateOrganization(OrganizationUpdateInDto organizationUpdateInDto) {
        Organization organization = getOrgById(organizationUpdateInDto.getId());
        organization = organizationMapper.organizationInToDomainUpdate(organizationUpdateInDto, organization);
        organizationRepository.save(organization);
        return new ResponseEntity<>(new SuccessDto(), HttpStatus.OK);
    }

    /**
     * Служебный метод для получения организации по id
     *
     * @param id Уникальный идентификатор организации
     * @return Organization
     */
    public Organization getOrgById(Integer id) {
        return organizationRepository
                .findById(id).orElseThrow(() -> new anyUserErrorException(ORGANIZATION_NOT_FOUND + id));
    }

    /**
     * метод для сохранения организации из очереди
     *
     * @param message
     * @throws InterruptedException - для понта
     */
    @RabbitListener(queues = RabbitMQConfig.QUERY_SAVE_ORGANIZATION)
    public void processingOrganizations(String message) throws InterruptedException, JsonProcessingException {
        OrganizationSaveInDto organizationSaveInDto = objectMapper.readValue(message,OrganizationSaveInDto.class);
        Thread.sleep(2000);
        addOrganization(organizationSaveInDto);
    }

    /**
     * Метод для получения организации по id из очереди
     *
     * @param id Уникальный идентификатор организации
     * @return OrganizationOutDto
     */
    @RabbitListener(queues = RabbitMQConfig.NAME_QUEUE_GET_ORGANIZATION)
    public void getOrganizationByIdQueue(Integer id) throws JsonProcessingException {
        Organization organization = getOrgById(id);
        OrganizationOutDto organizationOutDto = organizationMapper.organizationToDto(organization);
        rabbitTemplate.convertAndSend(RabbitMQConfig.NAME_QUEUE_RETURN_ORGANIZATION,objectMapper.writeValueAsString(organizationOutDto));
    }
}

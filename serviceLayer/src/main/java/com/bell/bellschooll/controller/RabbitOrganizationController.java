package com.bell.bellschooll.controller;

import com.bell.bellschooll.config.RabbitMQConfig;
import com.bell.bellschooll.dto.request.MessageDto;
import com.bell.bellschooll.dto.request.OrganisationDtoRequest;
import com.bell.bellschooll.dto.request.OrganizationSaveInDto;
import com.bell.bellschooll.dto.response.OrganizationOutDto;
import com.bell.bellschooll.dto.response.SuccessDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * Контроллер для работы с очередью RabbitMQ
 */
@Log4j2
@Component
public class RabbitOrganizationController {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final OrganizationController organizationController;

    public RabbitOrganizationController(RabbitTemplate rabbitTemplate
            , OrganizationController organizationController) {
        this.rabbitTemplate = rabbitTemplate;
        this.organizationController = organizationController;
    }

    /**
     * Метод для получения организации по id из очереди
     *
     * @param message сообщение из очереди
     */
    @RabbitListener(queues = RabbitMQConfig.NAME_QUEUE_GET_ORGANIZATION)
    public void getOrganizationFromQueue(String message) throws JsonProcessingException, InterruptedException {
        log.log(Level.INFO, "Получен объект из очереди: " + message);
        Thread.sleep(5000);//имитируем асинхронное выполнение
        MessageDto messageFromQueue = objectMapper.readValue(message, MessageDto.class);
        int idMessage = messageFromQueue.getId();
        String method = messageFromQueue.getMethod();

        Object body = null;
        switch (method) {
            case "get":
                Integer id = objectMapper.convertValue(messageFromQueue.getBody(), Integer.class);
                ResponseEntity<OrganizationOutDto> organizationById = organizationController.getOrganizationById(id);
                body = organizationById.getBody();
                break;
            case "list":
                OrganisationDtoRequest organisationDtoRequest = objectMapper
                        .convertValue(messageFromQueue.getBody(), OrganisationDtoRequest.class);
                body = organizationController.getListOrganization(organisationDtoRequest).getBody();
                break;
        }
        MessageDto messageDtoToQueue = new MessageDto();
        messageDtoToQueue.setId(idMessage);
        messageDtoToQueue.setMethod(method);
        messageDtoToQueue.setBody(body);
        rabbitTemplate.convertAndSend(RabbitMQConfig.NAME_QUEUE_RETURN_ORGANIZATION
                , objectMapper.writeValueAsString(messageDtoToQueue));
        assert body != null;
        log.log(Level.INFO, "В очередь отправлен Объект:" + body.toString());
    }

    /**
     * метод для сохранения организации из очереди
     *
     * @param message
     * @throws InterruptedException - для понта
     */
    @RabbitListener(queues = RabbitMQConfig.QUERY_SAVE_ORGANIZATION)
    public void processingOrganizations(String message) throws InterruptedException, JsonProcessingException {
        log.log(Level.INFO, "Из очереди получен объект: " + message);
        OrganizationSaveInDto organizationSaveInDto = objectMapper.readValue(message, OrganizationSaveInDto.class);
        Thread.sleep(2000);
        organizationController.addOrganization(organizationSaveInDto);
        log.log(Level.INFO, "Объект сохранен в БД: " + message);
    }

    /**
     * Сохранение новой организации Через очередь RabbitMQ
     *
     * @param organizationSaveInDto Объект, содержащий  параметры новой организации
     * @return SuccessDto
     */
    @PostMapping("save/queue")
    public ResponseEntity<SuccessDto> addOrganizationQueue(
            @Valid @RequestBody OrganizationSaveInDto organizationSaveInDto) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUERY_SAVE_ORGANIZATION, organizationSaveInDto);
        return new ResponseEntity<>(new SuccessDto(), HttpStatus.OK);
    }
}

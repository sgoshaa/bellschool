package com.bell.bellschooll.controller;

import com.bell.bellschooll.config.KafkaTopicConfig;
import com.bell.bellschooll.dto.request.MessageDto;
import com.bell.bellschooll.dto.request.OfficeInListDto;
import com.bell.bellschooll.dto.request.OfficeInSaveDto;
import com.bell.bellschooll.dto.request.OfficeInUpdateDto;
import com.bell.bellschooll.dto.response.OfficeListOutDto;
import com.bell.bellschooll.dto.response.OfficeOutDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Log4j2
@Component
public class KafkaOfficeController {

    private final KafkaTemplate<String, MessageDto> kafkaTemplate;
    private final OfficeController officeController;
    private final ObjectMapper objectMapper;

    public KafkaOfficeController(KafkaTemplate<String, MessageDto> kafkaTemplate
            , OfficeController officeController, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.officeController = officeController;
        this.objectMapper = objectMapper;
    }

    /**
     * Метод который слушает очередь в kafka и забирает из очереди сообщения
     *
     * @param messageFromQueue входящее сообщение
     */
    @KafkaListener(groupId = "groupId",
            topics = KafkaTopicConfig.QUEUE_OFFICE,
            containerFactory = "listenerContainerFactory")
    public void getOfficeFromQueue(MessageDto messageFromQueue) {
        log.log(Level.INFO, "Получен запрос с объектом: " + messageFromQueue);
        MessageDto messageDto = null;
        switch (messageFromQueue.getMethod()) {
            case "get":
                ResponseEntity<OfficeOutDto> officeById = officeController
                        .getOfficeById((Integer) messageFromQueue.getBody());
                messageDto = getMessageDto(messageFromQueue, officeById.getBody());
                break;
            case "save":
                OfficeInSaveDto officeInSaveDto = objectMapper
                        .convertValue(messageFromQueue.getBody(), OfficeInSaveDto.class);
                officeController.addOffice(officeInSaveDto);
                break;
            case "list":
                OfficeInListDto officeInListDto = objectMapper
                        .convertValue(messageFromQueue.getBody(), OfficeInListDto.class);
                ResponseEntity<List<OfficeListOutDto>> list = officeController.listOffice(officeInListDto);
                messageDto = getMessageDto(messageFromQueue, list.getBody());
                break;
            case "update":
                OfficeInUpdateDto officeInUpdateDto = objectMapper
                        .convertValue(messageFromQueue.getBody(), OfficeInUpdateDto.class);
                officeController.updateOffice(officeInUpdateDto);
        }

        if (messageDto != null) {
            kafkaTemplate.send(KafkaTopicConfig.QUEUE_RETURN_OFFICE, messageDto);
            log.log(Level.INFO, "Отправлен ответ с объектом: " + messageDto);
        }
    }

    private MessageDto getMessageDto(MessageDto messageFromQueue, Object body) {
        MessageDto messageDto;
        messageDto = new MessageDto();
        messageDto.setMethod(messageFromQueue.getMethod());
        messageDto.setId(messageFromQueue.getId());
        messageDto.setBody(body);
        return messageDto;
    }
}

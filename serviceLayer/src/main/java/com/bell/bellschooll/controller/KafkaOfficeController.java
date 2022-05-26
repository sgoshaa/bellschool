package com.bell.bellschooll.controller;

import com.bell.bellschooll.config.KafkaTopicConfig;
import com.bell.bellschooll.config.MessageDto;
import com.bell.bellschooll.dto.response.OfficeOutDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class KafkaOfficeController {

    private final KafkaTemplate<String, MessageDto> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final OfficeController officeController;

    public KafkaOfficeController(KafkaTemplate<String, MessageDto> kafkaTemplate, ObjectMapper objectMapper, OfficeController officeController) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
        this.officeController = officeController;
    }

    @KafkaListener(groupId = "groupId", topics = KafkaTopicConfig.QUEUE_OFFICE)
    public void getOfficeFromQueue(String msg) throws JsonProcessingException {
        log.log(Level.INFO, "Получен запрос с объектом: " + msg);
        MessageDto messageFromQueue = objectMapper.readValue(msg, MessageDto.class);

        ResponseEntity<OfficeOutDto> officeById = officeController.getOfficeById((Integer) messageFromQueue.getBody());
        MessageDto messageDto = new MessageDto();
        messageDto.setMethod(messageFromQueue.getMethod());
        messageDto.setId(messageFromQueue.getId());
        messageDto.setBody(officeById.getBody());

        kafkaTemplate.send(KafkaTopicConfig.QUEUE_RETURN_OFFICE, messageDto);
        log.log(Level.INFO, "Отправлен ответ с объектом: " + messageDto);
    }
}

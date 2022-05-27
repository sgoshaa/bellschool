package com.bell.bellschooll.config;


import com.bell.bellschooll.dto.request.MessageDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.logging.log4j.Level;

import java.util.Map;

@Log4j2
public class CustomJsonDeserializer implements Deserializer<MessageDto> {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public MessageDto deserialize(String topic, byte[] data) {
        MessageDto messageDto = null;
        try {
            if (data == null) {
                log.log(Level.INFO, "Null received at deserializing");
                return null;
            }
            messageDto = objectMapper.readValue(new String(data, "UTF-8"), MessageDto.class);
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to MessageDto");
        }
        return messageDto;
    }

    @Override
    public MessageDto deserialize(String topic, Headers headers, byte[] data) {
        return Deserializer.super.deserialize(topic, headers, data);
    }

    @Override
    public void close() {

    }
}

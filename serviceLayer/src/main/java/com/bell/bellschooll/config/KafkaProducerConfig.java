package com.bell.bellschooll.config;

import com.bell.bellschooll.dto.request.MessageDto;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Конфигурация KafkaProducerConfig
 */
@Configuration
public class KafkaProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String boostrapServers;

    /**
     * Метод для создания бина producerConfig
     *
     * @return Map<String, Object>
     */
    @Bean
    public Map<String, Object> producerConfig() {
        HashMap<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, boostrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

    /**
     * Метод для создания бина producerFactory
     *
     * @return ProducerFactory<String, MessageDto>
     */
    @Bean
    public ProducerFactory<String, MessageDto> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    /**
     * Метод для создания бина kafkaTemplate
     *
     * @param producerFactory
     * @return
     */
    @Bean
    public KafkaTemplate<String, MessageDto> kafkaTemplate(
            ProducerFactory<String, MessageDto> producerFactory
    ) {
        return new KafkaTemplate<>(producerFactory);
    }
}

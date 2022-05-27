package com.bell.bellschooll.config;

import com.bell.bellschooll.dto.request.MessageDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * Конфигурация KafkaConsumer
 */
@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String boostrapServers;

    /**
     * Метод для создания бина consumerConfig
     *
     * @return Map<String, Object>
     */
    @Bean
    public Map<String, Object> consumerConfig() {
        HashMap<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, boostrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, CustomJsonDeserializer.class);
        return props;
    }

    /**
     * Метод для создания бина consumerFactory
     *
     * @return ConsumerFactory<String, MessageDto>
     */
    @Bean
    public ConsumerFactory<String, MessageDto> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }

    /**
     * Метод для создания бина
     *
     * @param consumerFactory listenerContainerFactory
     * @return KafkaListenerContainerFactory<ConcurrentMessageListenerContainer < String, MessageDto>>
     */
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, MessageDto>>
    listenerContainerFactory(
            ConsumerFactory<String, MessageDto> consumerFactory
    ) {
        ConcurrentKafkaListenerContainerFactory<String, MessageDto> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }
}

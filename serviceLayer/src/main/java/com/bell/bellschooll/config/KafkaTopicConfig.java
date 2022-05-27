package com.bell.bellschooll.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * Конфигурация KafkaTopicConfig
 */
@Configuration
public class KafkaTopicConfig {

    public static final String QUEUE_OFFICE = "queue_office";
    public static final String QUEUE_RETURN_OFFICE = "queue_return_office";

    /**
     * Метод для создания бина очереди
     *
     * @return NewTopic
     */
    @Bean
    public NewTopic topic() {
        return TopicBuilder.name(QUEUE_OFFICE).build();
    }

    /**
     * Метод для создания бина очереди
     *
     * @return NewTopic
     */
    @Bean
    public NewTopic returnTopic() {
        return TopicBuilder.name(QUEUE_RETURN_OFFICE).build();
    }
}

package com.bell.bellschooll.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUERY_SAVE_ORGANIZATION = "query-save-organization";
    public static final String HOST_NAME = "localhost";

    @Bean
    public Queue myQueue() {
        return new Queue(QUERY_SAVE_ORGANIZATION);
    }

    @Bean
    public FanoutExchange fanout() {
        return new FanoutExchange("Fanout");
    }

    @Bean
    public Binding binding(FanoutExchange exchange, Queue queue) {
        return BindingBuilder.bind(queue).to(exchange);
    }

    @Bean
    public CachingConnectionFactory connectionFactory() {
        return new CachingConnectionFactory(HOST_NAME);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

}

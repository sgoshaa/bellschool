package com.bell.bellschooll.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUERY_SAVE_ORGANIZATION = "queue-save-organization";
    public static final String NAME_QUEUE_GET_ORGANIZATION = "queue-get-organization";
    public static final String NAME_QUEUE_RETURN_ORGANIZATION = "queue-return-organization";
    public static final String HOST_NAME = "localhost";

    @Bean("myQueue")
    public Queue myQueue() {
        return new Queue(QUERY_SAVE_ORGANIZATION);
    }

    @Bean
    public Queue queueGetOrganization() {
        return new Queue(NAME_QUEUE_GET_ORGANIZATION);
    }

    @Bean
    public Queue queueReturnOrganization() {
        return new Queue(NAME_QUEUE_RETURN_ORGANIZATION);
    }

    @Bean
    public FanoutExchange fanout() {
        return new FanoutExchange("Fanout");
    }

    @Bean
    public Binding binding(FanoutExchange exchange, @Qualifier("myQueue") Queue queue) {
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

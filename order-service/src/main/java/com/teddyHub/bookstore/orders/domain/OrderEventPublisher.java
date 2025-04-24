package com.teddyHub.bookstore.orders.domain;

import com.teddyHub.bookstore.orders.ApplicationProperties;
import com.teddyHub.bookstore.orders.domain.models.OrderCreatedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderEventPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final ApplicationProperties properties;

    OrderEventPublisher(RabbitTemplate rabbitTemplate, ApplicationProperties properties) {
        this.rabbitTemplate = rabbitTemplate;
        this.properties = properties;
    }

    public void publish(OrderCreatedEvent event) {
        this.send(properties.newOrdersQueue(), event);
    }

    private void send(String routingKey, Object payload) {
        rabbitTemplate.convertAndSend(properties.orderEventsExchange(), routingKey, payload);
    }
}

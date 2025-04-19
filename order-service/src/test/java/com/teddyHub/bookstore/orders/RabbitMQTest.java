package com.teddyHub.bookstore.orders;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RabbitMQTest {
    @Autowired
    private RabbitAdmin rabbitAdmin;

    @Test
    void verifyExchange() {
        String exchange = rabbitAdmin.getRabbitTemplate()
                .execute(channel -> String.valueOf(
                        channel.exchangeDeclarePassive("orders-exchange")));

        System.out.println("Exchange exists: " + exchange);
    }
}

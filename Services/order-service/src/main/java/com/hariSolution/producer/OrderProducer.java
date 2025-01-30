package com.hariSolution.producer;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.internals.Topic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Component
@RequiredArgsConstructor
public class OrderProducer {
    private final KafkaTemplate<String, OrderConfirmation> kafkaTemplate;

    public void sendOrderConfirmationMessage(OrderConfirmation orderConfirmation) {

        Message<OrderConfirmation> message = MessageBuilder
                .withPayload(orderConfirmation)
                .setHeader(TOPIC, "order-confirmation").build();

        kafkaTemplate.send(message);

    }
}

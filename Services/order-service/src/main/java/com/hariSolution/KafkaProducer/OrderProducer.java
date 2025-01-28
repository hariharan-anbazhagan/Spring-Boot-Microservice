package com.hariSolution.KafkaProducer;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProducer {

    private final KafkaTemplate<String, OrderConfirmation> kafkaTemplate;

    public void orderConfirmation(OrderConfirmation orderConfirmation) {
        try {
            // Build the Kafka message
            Message<OrderConfirmation> message = MessageBuilder
                    .withPayload(orderConfirmation)
                    .setHeader(KafkaHeaders.TOPIC, "order-confirm")
                    .build();

            // Send the message
            kafkaTemplate.send(message);

            // Log the sent message for debugging
            System.out.println("Message sent to Kafka: " + message);

        } catch (Exception e) {
            // Log the error for debugging
            System.err.println("Failed to send message to Kafka: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

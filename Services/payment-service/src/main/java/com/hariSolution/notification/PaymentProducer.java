package com.hariSolution.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Service
@RequiredArgsConstructor
public class PaymentProducer {
    private final KafkaTemplate<String, PaymentConfirmation> kafkaTemplate;

    public void sendMessageToPaymentConfirmation(PaymentConfirmation confirmation) {
        Message<PaymentConfirmation> message = MessageBuilder
                .withPayload(confirmation)
                .setHeader(TOPIC, "payment-confirmation").build();
        kafkaTemplate.send(message);


    }
}

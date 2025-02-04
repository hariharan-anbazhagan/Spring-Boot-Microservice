package com.hariSolution.kafka.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j  // Enables logging
public class PaymentProducer {

    private final KafkaTemplate<String, PaymentConfirmation> kafkaTemplate;

    public void sendMessageToPaymentConfirmation(PaymentConfirmation confirmation) {
        if (confirmation == null) {
            log.warn("Attempted to send null PaymentConfirmation message. Ignoring...");
            return;
        }

        /*Message<PaymentConfirmation> message = MessageBuilder
                    .withPayload(confirmation)
                    .setHeader(TOPIC, "payment-confirmation")
                    .build();*/

         kafkaTemplate.send("payment-confirmation",confirmation);


    }
}

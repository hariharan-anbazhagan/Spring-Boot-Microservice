package com.hariSolution.kafka.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentProducer {

    private final KafkaTemplate<String, PaymentConfirmation> kafkaTemplate;

    public void sendMessageToPaymentConfirmation(PaymentConfirmation confirmation) {


        /*Message<PaymentConfirmation> message = MessageBuilder
                    .withPayload(confirmation)
                    .setHeader(TOPIC, "payment-confirmation")
                    .build();*/

         kafkaTemplate.send("payment-confirmation",confirmation);
        System.out.println(confirmation);


    }
}

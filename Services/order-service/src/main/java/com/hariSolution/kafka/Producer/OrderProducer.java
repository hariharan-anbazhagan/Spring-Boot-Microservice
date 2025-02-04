package com.hariSolution.kafka.Producer;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderProducer {
    private final KafkaTemplate<String, OrderConfirmation> kafkaTemplate;


    public void sendOrderConfirmationMessage(OrderConfirmation orderConfirmation) {

       /* Message<OrderConfirmation> message = MessageBuilder
                .withPayload(orderConfirmation)
                .setHeader(TOPIC, "order-confirmation")
                .build();
*/

        kafkaTemplate.send("order-confirmation",orderConfirmation);
        System.out.println(orderConfirmation);
    }
}

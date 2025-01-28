package com.hariSolution.Notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationProducer {
private final KafkaTemplate<String, PaymentConfirmation> paymentNotification;

public void sendNotification(PaymentConfirmation request){
    Message<PaymentConfirmation> message= MessageBuilder.withPayload(request)
            .setHeader(TOPIC,"payment-confirm").build();
}

}

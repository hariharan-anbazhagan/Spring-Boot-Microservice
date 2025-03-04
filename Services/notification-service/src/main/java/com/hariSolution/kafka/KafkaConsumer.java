package com.hariSolution.kafka;

import com.hariSolution.Mapper.NotificationMapper;
import com.hariSolution.payment.PaymentConfirmation;
import com.hariSolution.kafkaOrder.order.OrderConfirmation;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {


    private final NotificationMapper notificationMapper;

    public KafkaConsumer(NotificationMapper notificationMapper) {
        this.notificationMapper = notificationMapper;
    }

    // Order Confirmation Listener
    @KafkaListener(
            topics = "order-confirmation",
            groupId = "orderGroup",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void sendOrderConfirmation( OrderConfirmation orderConfirmation) {

        System.out.println("Received Employee Details: " + orderConfirmation);

    }

    // Payment Confirmation Listener
    @KafkaListener(
            topics = "payment-confirmation",
            groupId = "paymentGroup",
            containerFactory = "kafkaListenerContainersFactory"
    )
    public void sendPaymentConfirmation( PaymentConfirmation paymentConfirmation) {

        System.out.println("Received Payment Details: " + paymentConfirmation);
    }
}

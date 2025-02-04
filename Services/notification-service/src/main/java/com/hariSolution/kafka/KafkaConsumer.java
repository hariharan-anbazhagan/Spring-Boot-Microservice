package com.hariSolution.kafka;

import com.hariSolution.Mapper.NotificationMapper;
import com.hariSolution.notification.Notification;
import com.hariSolution.notification.NotificationRepository;
import com.hariSolution.notification.NotificationType;
import com.hariSolution.payment.PaymentConfirmation;
import com.hariSolution.kafkaOrder.order.OrderConfirmation;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class KafkaConsumer {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    public KafkaConsumer(NotificationRepository notificationRepository, NotificationMapper notificationMapper) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
    }

    // Order Confirmation Listener
    @KafkaListener(
            topics = "order-confirmation",
            groupId = "orderGroup",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void sendOrderConfirmation( OrderConfirmation orderConfirmation) {

        Notification notification= this.notificationRepository.save(this.notificationMapper.toOrderNotification(orderConfirmation));

        System.out.println(notification);

        System.out.println("Received Employee Details: " + orderConfirmation);

    }

    // Payment Confirmation Listener
    @KafkaListener(
            topics = "payment-confirmation",
            groupId = "paymentGroup",
            containerFactory = "kafkaListenerContainersFactory"
    )
    public void sendPaymentConfirmation( PaymentConfirmation paymentConfirmation) {
        Notification notification= this.notificationRepository.save(this.notificationMapper.toPaymentNotification(paymentConfirmation));

        System.out.println(notification);

        System.out.println("Received Employee Details: " + paymentConfirmation);
    }
}

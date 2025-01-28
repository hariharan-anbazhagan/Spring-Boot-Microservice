package com.hariSolution.kafka.consumer;

import com.hariSolution.email.EmailService;
import com.hariSolution.notification.Notification;
import com.hariSolution.notification.NotificationRepository;
import com.hariSolution.notification.NotificationType;
import com.hariSolution.notification.OrderConfirmation;
import com.hariSolution.payment.PaymentConfirmation;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j // Lombok annotation to auto-generate constructor for final fields
public class NotificationConsumer {

    private final NotificationRepository repository;
    private final EmailService emailService;

    public NotificationConsumer(NotificationRepository repository, EmailService emailService) {
        this.repository = repository;
        this.emailService = emailService;
    }

    @KafkaListener(topics = "payment-confirm", groupId = "paymentGroup")
    public void consumePaymentConfirmation(PaymentConfirmation paymentConfirmation) {
        try {
            // Create and save notification
            Notification notification = new Notification();
            notification.setNotificationType(NotificationType.PAYMENT_CONFIRMATION);
            notification.setNotificationDate(LocalDateTime.now());
            notification.setPaymentConfirmation(paymentConfirmation);
            repository.save(notification);

            // Extracting username for the email
            var userName = paymentConfirmation.getCustomerFirstname() + " " + paymentConfirmation.getCustomerLastname();

            // Send email for payment confirmation
            emailService.sendPaymentSuccessfully(
                    paymentConfirmation.getCustomerEmail(),
                    userName,
                    paymentConfirmation.getAmount(),
                    paymentConfirmation.getOrderReference()
            );

            System.out.println(notification);
            //log.info("Payment confirmation processed for order: {}", paymentConfirmation.getOrderReference());
        } catch (MessagingException e) {
            //log.error("Failed to send email for payment confirmation for order: {}", paymentConfirmation.getOrderReference(), e);
        } catch (Exception e) {
            //.error("An unexpected error occurred while processing payment confirmation for order: {}", paymentConfirmation.getOrderReference(), e);
        }
    }

    @KafkaListener(topics = "order-confirm", groupId = "orderGroup")
    public void consumeOrderConfirmation(OrderConfirmation orderConfirmation) {
        try {
            // Create and save notification
            Notification notification = new Notification();
            notification.setNotificationType(NotificationType.ORDER_CONFIRMATION);
            notification.setNotificationDate(LocalDateTime.now());
            notification.setOrderConfirmation(orderConfirmation);
            repository.save(notification);

            System.out.println(notification);

            // Extracting username for the email
            var userName = orderConfirmation.getUser().getFirstname() + " " + orderConfirmation.getUser().getLastname();

            // Send email for payment confirmation
            emailService.sendPaymentSuccessfully(
                    orderConfirmation.getUser().getEmail(),
                    userName,
                    orderConfirmation.getTotalAmount(),
                    orderConfirmation.getOrderReference()
            );

            //log.info("Payment confirmation processed for order: {}", paymentConfirmation.getOrderReference());
        } catch (MessagingException e) {
            //log.error("Failed to send email for payment confirmation for order: {}", paymentConfirmation.getOrderReference(), e);
        } catch (Exception e) {
            //log.error("An unexpected error occurred while processing payment confirmation for order: {}", paymentConfirmation.getOrderReference(), e);
        }
    }
}

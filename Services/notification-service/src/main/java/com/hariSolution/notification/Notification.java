package com.hariSolution.notification;

import com.hariSolution.config.JsonConverter;
import com.hariSolution.kafkaOrder.order.OrderConfirmation;
import com.hariSolution.payment.PaymentConfirmation;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notification_info")
public class Notification {

    private Integer id;

    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    private LocalDateTime notificationDate;

    @Convert(converter = JsonConverter.class)
    private OrderConfirmation orderConfirmation;

    @Convert(converter = JsonConverter.class)
    private PaymentConfirmation paymentConfirmation;

}

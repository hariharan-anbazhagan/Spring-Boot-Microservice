package com.hariSolution.Mapper;

import com.hariSolution.kafkaOrder.order.OrderConfirmation;
import com.hariSolution.notification.Notification;
import com.hariSolution.notification.NotificationType;
import com.hariSolution.payment.PaymentConfirmation;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class NotificationMapper {

    public Notification toOrderNotification(OrderConfirmation orderConfirmation){
        if(orderConfirmation==null){
            return null;
        }
        Notification notification=new Notification();
        notification.setOrderConfirmation(orderConfirmation);
        notification.setNotificationType(NotificationType.ORDER_CONFIRMATION);
        LocalDateTime now=LocalDateTime.now();
        notification.setNotificationDate(now);
        return notification;
    }

    public Notification toPaymentNotification(PaymentConfirmation paymentConfirmation){
        if(paymentConfirmation==null){
            return null;
        }
        Notification notification=new Notification();
        notification.setPaymentConfirmation(paymentConfirmation);
        notification.setNotificationType(NotificationType.PAYMENT_CONFIRMATION);
        LocalDateTime now=LocalDateTime.now();
        notification.setNotificationDate(now);
        return notification;
    }


}

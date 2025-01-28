package com.hariSolution.service;

import com.hariSolution.Mapper.PaymentMapper;
import com.hariSolution.Notification.NotificationProducer;
import com.hariSolution.Notification.PaymentConfirmation;
import com.hariSolution.model.Payment;
import com.hariSolution.model.PaymentRequest;
import com.hariSolution.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final NotificationProducer paymentNotificationRequest;

    public Integer createPayment(PaymentRequest request) {
        Payment payment = this.paymentRepository.save(this.paymentMapper.toPayment(request));

        PaymentConfirmation paymentRequest=new PaymentConfirmation();

        paymentRequest.setOrderReference( request.getOrderReference());
        paymentRequest.setAmount( request.getAmount());
        paymentRequest.setPaymentMethod(request.getPaymentMethod());
        paymentRequest.setCustomerFirstname( request.getUser().getFirstname());
        paymentRequest.setCustomerLastname(request.getUser().getLastname());
        paymentRequest.setCustomerEmail(request.getUser().getEmail());

        this.paymentNotificationRequest.sendNotification(paymentRequest);

        return payment.getId();
    }


}

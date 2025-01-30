package com.hariSolution.Mapper;

import com.hariSolution.model.Payment;
import com.hariSolution.model.PaymentRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PaymentMapper {
    public Payment toPayment(PaymentRequest request) {
        if (request == null) {
            return null;
        }

        Payment payment = new Payment();
        payment.setPaymentMethod(request.getPaymentMethod());
        payment.setAmount(request.getAmount());
        payment.setOrderId(request.getOrderId());
        payment.setUserId(request.getUserId());
        LocalDateTime now=LocalDateTime.now();
        payment.setCreatedDate(now);
        payment.setLastModifiedDate(now);
        return payment;
    }
}

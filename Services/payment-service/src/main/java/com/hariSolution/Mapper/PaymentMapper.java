package com.hariSolution.Mapper;

import com.hariSolution.model.Payment;
import com.hariSolution.model.PaymentRequest;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    public Payment toPayment(PaymentRequest request) {
        if (request == null) {
            return null;
        }

        Payment payment = new Payment();
        payment.setId(request.getId());
        payment.setPaymentMethod(request.getPaymentMethod());
        payment.setAmount(request.getAmount());
        payment.setOrderId(request.getOrderId());
        return payment;
    }
}

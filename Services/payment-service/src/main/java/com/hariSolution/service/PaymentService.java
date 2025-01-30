package com.hariSolution.service;

import com.hariSolution.Mapper.PaymentMapper;
import com.hariSolution.Mapper.PaymentResponseMapper;
import com.hariSolution.User.UserClient;
import com.hariSolution.User.UserDetails;
import com.hariSolution.model.Payment;
import com.hariSolution.model.PaymentRequest;
import com.hariSolution.model.PaymentResponse;
import com.hariSolution.notification.PaymentConfirmation;
import com.hariSolution.notification.PaymentProducer;
import com.hariSolution.repository.PaymentRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final PaymentProducer paymentProducer;
    private final PaymentResponseMapper paymentResponse;
    private final UserClient userClient;

    public PaymentResponse createPayment(@Valid PaymentRequest request) {


        if (request == null || request.getUserId() == null) {
            return paymentResponse.createResponse(
                    "Payment failed",
                    "Invalid payment request. User ID is missing.",
                    HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST
            );
        }


        UserDetails userDetails;
        try {
            userDetails = this.userClient.getUserDetailsByUserId(request.getUserId());
            if (userDetails == null) {
                return paymentResponse.createResponse(
                        "User details retrieval failed",
                        "User details not found for the provided user ID.",
                        HttpStatus.NOT_FOUND.value(),
                        HttpStatus.NOT_FOUND
                );
            }

        } catch (Exception e) {

            return paymentResponse.createResponse(
                    "User details retrieval failed",
                    "Unable to fetch user details. Please try again later.",
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

        System.out.println(userDetails);
        // Process payment
        Payment payment = paymentRepository.save(paymentMapper.toPayment(request));


        PaymentConfirmation paymentConfirmation = new PaymentConfirmation();
        paymentConfirmation.setOrderReference(request.getOrderReference());
        paymentConfirmation.setPaymentMethod(request.getPaymentMethod());
        paymentConfirmation.setAmount(request.getAmount());
        paymentConfirmation.setUserFirstname(userDetails.getFirstName());
        paymentConfirmation.setUserFullName(userDetails.getFullName());
        paymentConfirmation.setUserEmail(userDetails.getEmail());

        System.out.println(paymentConfirmation);

       // paymentProducer.sendMessageToPaymentConfirmation(paymentConfirmation);


        return paymentResponse.createResponse(
                "Payment processed successfully",
                "An amount of " + payment.getAmount() + " has been successfully processed.",
                HttpStatus.OK.value(),
                HttpStatus.OK
        );
    }
}

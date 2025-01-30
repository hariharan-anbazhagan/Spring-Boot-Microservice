package com.hariSolution.controller;

import com.hariSolution.model.PaymentRequest;
import com.hariSolution.model.PaymentResponse;
import com.hariSolution.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/click-payment")
    public ResponseEntity<PaymentResponse> payment(@RequestBody @Valid PaymentRequest request) {

        return ResponseEntity.ok(this.paymentService.createPayment(request));

    }

}

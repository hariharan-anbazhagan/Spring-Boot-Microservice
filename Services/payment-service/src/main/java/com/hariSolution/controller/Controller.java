package com.hariSolution.controller;

import com.hariSolution.model.PaymentMethod;
import com.hariSolution.model.PaymentRequest;
import com.hariSolution.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class Controller {
    private final PaymentService paymentService;

    @PostMapping("/click-payment")
    public ResponseEntity<Integer> payment(@RequestBody PaymentRequest request) {

        return ResponseEntity.ok(this.paymentService.createPayment(request));

    }

}

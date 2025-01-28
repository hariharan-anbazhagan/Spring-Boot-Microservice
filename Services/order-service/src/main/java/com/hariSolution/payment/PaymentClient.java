package com.hariSolution.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class PaymentClient {
    @Value("${application.config.payment-url}")
    private  String paymentUrl;

    private final RestTemplate restTemplate;

    public void getPaymentDetailsFromPaymentService(PaymentRequest request) {

        HttpHeaders headers = new HttpHeaders();
        //headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PaymentRequest> requestBody = new HttpEntity<>(request, headers);

        ParameterizedTypeReference<Integer> responseType = new ParameterizedTypeReference<Integer>() {
        };

        ResponseEntity<Integer> responseEntity = restTemplate.exchange(
                paymentUrl + "/click-payment", HttpMethod.POST, requestBody, responseType, request


        );

        if (responseEntity.getStatusCode().isError()) {

            throw new RuntimeException("An error occurred while processing the payment (" +
                    request + "): " + responseEntity.getStatusCode()
            );

        }
        responseEntity.getBody();


    }


}

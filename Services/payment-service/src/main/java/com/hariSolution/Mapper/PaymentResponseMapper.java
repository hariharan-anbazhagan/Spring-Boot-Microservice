package com.hariSolution.Mapper;


import com.hariSolution.model.PaymentResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Component
public class PaymentResponseMapper {

    public PaymentResponse createResponse(String message, String details, int status_value, HttpStatus status) {

        PaymentResponse response = new PaymentResponse();

        Map<String, Object> statusDetails = new HashMap<>();

        statusDetails.put("timestamp", Instant.now().toString());
        statusDetails.put("status", status.name());
        statusDetails.put("statusCode", status.value());

        Map<String, String> messageDetails = new HashMap<>();
        messageDetails.put("message", message);
        messageDetails.put("details", details);

        statusDetails.put("responseDetails", messageDetails);

        response.setStatus_details(statusDetails);

        return response;
    }
}

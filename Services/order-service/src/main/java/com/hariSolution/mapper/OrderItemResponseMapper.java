package com.hariSolution.mapper;


import com.hariSolution.orderIntem.OrderItemResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Component
public class OrderItemResponseMapper {

    public OrderItemResponse createResponse(String message, String details, int status_value, HttpStatus status) {

        OrderItemResponse response = new OrderItemResponse();

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

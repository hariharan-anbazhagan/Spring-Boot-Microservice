package com.hariSolution.mapper;

import com.hariSolution.order.OrderDetailsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.LinkedHashMap;

@Component
public class OrderDetailsResponseMapper {

    public OrderDetailsResponse createResponse(LinkedHashMap<String, Object> data, String status, int statusCode, HttpStatus statusMessage) {
        // Validate input parameters
        if (statusMessage == null) {
            throw new IllegalArgumentException("HttpStatus statusMessage cannot be null");
        }

        OrderDetailsResponse response = new OrderDetailsResponse();

        // Build status details
        LinkedHashMap<String, Object> statusDetails = new LinkedHashMap<>();
        statusDetails.put("status", status); // Status message (e.g., success/failure)
        statusDetails.put("statusCode", statusCode); // HTTP status code
        statusDetails.put("statusMessage", statusMessage.getReasonPhrase()); // HTTP status message
        statusDetails.put("serverTimestamp", Instant.now().toString()); // Timestamp of the response

        // Set response details
        response.setStatus_details(statusDetails);
        response.setData(data);

        return response;
    }
}

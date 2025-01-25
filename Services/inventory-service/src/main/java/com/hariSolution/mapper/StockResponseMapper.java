package com.hariSolution.mapper;

import com.hariSolution.model.StockResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.LinkedHashMap;

@Component
public class StockResponseMapper {

    public StockResponse createResponse(LinkedHashMap<String, Object> data, String status, int statusCode, HttpStatus statusMessage) {
        // Validate input parameters
        if (statusMessage == null) {
            throw new IllegalArgumentException("HttpStatus statusMessage cannot be null");
        }

        StockResponse response = new StockResponse();

        LinkedHashMap<String, Object> statusDetails = new LinkedHashMap<>();
        statusDetails.put("status", status);
        statusDetails.put("statusCode", statusCode);
        statusDetails.put("statusMessage", statusMessage.getReasonPhrase());
        statusDetails.put("serverTimestamp", Instant.now().toString());

        response.setStatus_details(statusDetails);
        response.setData(data);

        return response;
    }
}

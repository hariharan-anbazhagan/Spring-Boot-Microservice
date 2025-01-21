package com.hariSolution.Mapper;

import com.hariSolution.model.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserResponseMapper {

    public UserResponse createResponse(String message, String details, HttpStatus status) {

        UserResponse response = new UserResponse();

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

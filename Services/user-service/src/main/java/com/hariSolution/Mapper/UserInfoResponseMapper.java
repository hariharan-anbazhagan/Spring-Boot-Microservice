package com.hariSolution.Mapper;

import com.hariSolution.model.UserInfoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class UserInfoResponseMapper {

    public UserInfoResponse createResponse(Map<String, Object> data, String status, int statusCode, HttpStatus statusMessage) {

        UserInfoResponse response = new UserInfoResponse();

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

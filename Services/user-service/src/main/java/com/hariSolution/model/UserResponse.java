package com.hariSolution.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Component
@Data
public class UserResponse {
    private Map<String,Object> status_details;
}

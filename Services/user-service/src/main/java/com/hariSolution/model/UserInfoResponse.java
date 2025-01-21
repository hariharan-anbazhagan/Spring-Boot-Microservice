package com.hariSolution.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserInfoResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Map<String,Object> data;
    private Map<String,Object> status_details;
}

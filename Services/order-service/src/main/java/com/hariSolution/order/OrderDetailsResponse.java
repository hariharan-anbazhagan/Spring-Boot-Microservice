package com.hariSolution.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedHashMap;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class OrderDetailsResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private LinkedHashMap<String,Object> data;
    private LinkedHashMap<String,Object> status_details;

}

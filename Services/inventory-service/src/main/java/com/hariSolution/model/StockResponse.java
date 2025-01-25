package com.hariSolution.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedHashMap;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class StockResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private LinkedHashMap<String, Object> data;
    private LinkedHashMap<String, Object> status_details;

    public LinkedHashMap<String, Object> getData() {
        return data;
    }

    public void setData(LinkedHashMap<String, Object> data) {
        this.data = data;
    }

    public LinkedHashMap<String, Object> getStatus_details() {
        return status_details;
    }

    public void setStatus_details(LinkedHashMap<String, Object> status_details) {
        this.status_details = status_details;
    }
}

package com.hariSolution.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
@JsonSerialize
public class ProductCartResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer productId;
    private String name;
    private String descriptions;
    private Boolean isAvailable;
    private Double availableQuantity;
    private BigDecimal price;
    private String categoryName;
}

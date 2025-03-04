package com.hariSolution.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonSerialize
public class ProductDetails implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private Integer productId;
    private String name;
    private Boolean isAvailable;
    private String categoryName;
    private Integer quantity = 1;
    private BigDecimal price = BigDecimal.ZERO;
}

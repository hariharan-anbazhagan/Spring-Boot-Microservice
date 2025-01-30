package com.hariSolution.Order;

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
public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Integer productId;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
}

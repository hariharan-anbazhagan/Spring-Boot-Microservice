package com.hariSolution.product;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonSerialize
public class ProductPurchaseResponse implements Serializable {
    private Integer productId;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
}

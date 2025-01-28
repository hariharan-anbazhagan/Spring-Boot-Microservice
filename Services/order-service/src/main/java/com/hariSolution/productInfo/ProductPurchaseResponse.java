package com.hariSolution.productInfo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductPurchaseResponse {
    private Integer productId;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
}

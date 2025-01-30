package com.hariSolution.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderResponse implements Serializable {

    private String reference;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private Integer userId;
}

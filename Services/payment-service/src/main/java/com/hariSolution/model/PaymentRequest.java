package com.hariSolution.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hariSolution.User.User;
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
public class PaymentRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private Integer orderId;
    private String orderReference;
    private User user;
}

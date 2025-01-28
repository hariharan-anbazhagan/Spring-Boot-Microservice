package com.hariSolution.payment;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hariSolution.User.UserDetails;
import com.hariSolution.Order.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentRequest{

    private Integer id;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private Integer orderId;
    private String orderReference;
    private UserDetails user;
}

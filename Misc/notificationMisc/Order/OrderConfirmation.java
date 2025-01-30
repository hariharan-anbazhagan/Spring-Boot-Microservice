package com.hariSolution.Order;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hariSolution.payment.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonSerialize
public class OrderConfirmation implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String orderReference;
    private BigDecimal totalAmount;
    private PaymentMethod paymentMethod;
    private User customer;
    private List<Product> products;

}

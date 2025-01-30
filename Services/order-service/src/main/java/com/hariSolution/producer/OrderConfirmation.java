package com.hariSolution.producer;

import com.hariSolution.User.UserDetails;
import com.hariSolution.order.PaymentMethod;
import com.hariSolution.product.ProductPurchaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderConfirmation implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String orderReference;
    private BigDecimal totalAmount;
    private PaymentMethod paymentMethod;
    private UserDetails user;
    private ProductPurchaseResponse products;
}

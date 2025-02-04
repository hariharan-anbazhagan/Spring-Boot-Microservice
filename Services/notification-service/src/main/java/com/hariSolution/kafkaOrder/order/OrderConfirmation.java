package com.hariSolution.kafkaOrder.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hariSolution.notification.PaymentMethod;
import com.hariSolution.notification.UserDetails;
import com.hariSolution.product.ProductPurchaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderConfirmation {

    private String orderReference;
    private BigDecimal totalAmount;
    private PaymentMethod paymentMethod;
    private UserDetails user;
    private ProductPurchaseResponse products;
}

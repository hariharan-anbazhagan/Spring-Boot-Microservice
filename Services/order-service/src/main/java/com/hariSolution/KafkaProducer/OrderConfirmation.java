package com.hariSolution.KafkaProducer;

import com.hariSolution.User.UserDetails;
import com.hariSolution.Order.PaymentMethod;
import com.hariSolution.productInfo.ProductPurchaseResponse;
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
public class OrderConfirmation {

    private String orderReference;
    private BigDecimal totalAmount;
    private PaymentMethod paymentMethod;
    private UserDetails user;
    private List<ProductPurchaseResponse> products;

}

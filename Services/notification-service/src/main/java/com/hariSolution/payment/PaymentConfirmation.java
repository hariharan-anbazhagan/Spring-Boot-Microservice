package com.hariSolution.payment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hariSolution.notification.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentConfirmation  {

    private String orderReference;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private String userFirstname;
    private String userFullName;
    private String userEmail;
}

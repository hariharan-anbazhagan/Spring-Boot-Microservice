package com.hariSolution.kafka.notification;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hariSolution.model.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentConfirmation{

    private String orderReference;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private String userFirstname;
    private String userFullName;
    private String userEmail;


}

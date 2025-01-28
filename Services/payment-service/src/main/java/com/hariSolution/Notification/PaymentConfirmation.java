package com.hariSolution.Notification;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
@JsonSerialize
public class PaymentConfirmation implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String orderReference;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private String customerFirstname;
    private String customerLastname;
    private String customerEmail;
}

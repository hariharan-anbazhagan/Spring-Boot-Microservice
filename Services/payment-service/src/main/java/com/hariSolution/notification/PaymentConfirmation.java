package com.hariSolution.notification;

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
public class PaymentConfirmation implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String orderReference;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private String userFirstname;
    private String userFullName;
    private String userEmail;


}

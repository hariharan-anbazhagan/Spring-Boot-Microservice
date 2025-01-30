package com.hariSolution.model;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than zero")
    private BigDecimal amount;

    @NotBlank(message = "Order reference is required")
    @Size(min = 3, max = 50, message = "Order reference must be between 3 and 50 characters")
    private String orderReference;

    @NotNull(message = "Payment method is required")
    private PaymentMethod paymentMethod;

    @NotNull(message = "Order ID is required")
    @Positive(message = "Order ID must be a positive number")
    private Integer orderId;

    @NotNull(message = "User ID is required")
    @Positive(message = "User ID must be a positive number")
    private Integer userId;

}

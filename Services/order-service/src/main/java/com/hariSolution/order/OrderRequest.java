package com.hariSolution.order;

import com.hariSolution.product.ProductPurchaseRequest;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderRequest implements Serializable {

    @NotBlank(message = "Reference is required")
    @Size(min = 3, max = 50, message = "Reference must be between 3 and 50 characters")
    private String reference;

    @NotNull(message = "Order amount is required")
    @Positive(message = "Order amount should be positive")
    private BigDecimal totalAmount;

    @NotNull(message = "Payment method should be specified")
    private PaymentMethod paymentMethod;

    @NotNull(message = "Customer ID is required")
    @Positive(message = "Customer ID should be a positive number")
    private Integer userId;

    @NotNull(message = "You should at least purchase one product")
    private ProductPurchaseRequest product;


}

package com.hariSolution.orderIntem;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderItemRequest implements Serializable {

    @NotNull(message = "Order ID is required")
    private Integer orderId;

    @NotNull(message = "Product ID is required")
    private Integer productId;

    @NotBlank(message = "Product name cannot be blank")
    private String productName;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

}

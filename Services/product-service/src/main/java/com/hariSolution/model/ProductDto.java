package com.hariSolution.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class ProductDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Product name is mandatory")
    @Size(max = 100, message = "Product name cannot exceed 100 characters")
    private String name;

    @NotBlank(message = "Product description is mandatory")
    @Size(max = 500, message = "Product description cannot exceed 500 characters")
    private String descriptions;

    @NotNull(message = "Availability status is required")
    private Boolean isAvailable;

    @NotNull(message = "Availability status is required")
    private Double availableQuantity;

    @NotNull(message = "Product price is mandatory")
    @DecimalMin(value = "0.0", inclusive = false, message = "Product price must be greater than zero")
    @Digits(integer = 10, fraction = 2, message = "Product price must be a valid number with up to 10 digits and 2 decimal places")
    private BigDecimal price;

    @NotBlank(message = "Category name is mandatory")
    @Size(max = 100, message = "Category name cannot exceed 100 characters")
    private String categoryName;
}

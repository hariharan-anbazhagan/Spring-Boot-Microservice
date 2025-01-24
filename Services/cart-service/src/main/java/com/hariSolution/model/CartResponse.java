package com.hariSolution.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonSerialize
public class CartResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private List<ProductDetails> cartItems = new ArrayList<>();

    private BigDecimal totalPrice = BigDecimal.ZERO;  // Default value to avoid null

    private Boolean isCheckedOut = false;
}

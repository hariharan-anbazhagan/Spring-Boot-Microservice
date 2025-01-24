package com.hariSolution.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize
public class Cart implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private List<CartItem> cartItems = new ArrayList<>();

    private BigDecimal totalPrice = BigDecimal.ZERO; // Set default value


    private Boolean isCheckedOut = false; // Set default value
}

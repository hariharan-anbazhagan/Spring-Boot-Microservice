package com.hariSolution.OrderItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderItemRequest implements Serializable {
    private Integer orderId;
    private Integer productId;
    private Integer quantity;

}

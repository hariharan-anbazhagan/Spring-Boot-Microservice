package com.hariSolution.orderIntem;

import com.hariSolution.order.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "orderItem_details")
public class OrderItem implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(nullable = false)
    private Integer productId;

    @Column(nullable = false)
    private String  productName;

    @Column(nullable = false)
    private Integer quantity;
}

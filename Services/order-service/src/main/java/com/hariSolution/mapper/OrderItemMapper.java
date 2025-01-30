package com.hariSolution.mapper;

import com.hariSolution.order.Order;
import com.hariSolution.orderIntem.OrderItem;
import com.hariSolution.orderIntem.OrderItemRequest;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {

    public OrderItem toOrderItem(OrderItemRequest request) {
        OrderItem orderItem = new OrderItem();

        if (request.getOrderId() != null) {
            Order order = new Order();
            order.setId(request.getOrderId());
            orderItem.setOrder(order);
        } else {

            throw new IllegalArgumentException("Order ID cannot be null");
        }

        orderItem.setProductId(request.getProductId());
        orderItem.setProductName(request.getProductName());
        orderItem.setQuantity(request.getQuantity());

        return orderItem;
    }

}

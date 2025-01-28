package com.hariSolution.OrderItem;

import com.hariSolution.Order.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {

    public OrderItem toOrderItem(OrderItemRequest request) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(request.getProductId());
        Order order = new Order();
        order.setId(request.getOrderId());
        orderItem.setOrder(order);
        orderItem.setQuantity(request.getQuantity());

        return orderItem;

    }

    public OrderItemResponse toOrderItemResponse(OrderItem orderItem) {
        OrderItemResponse orderItemResponse = new OrderItemResponse();
        orderItemResponse.setId(orderItem.getId());
        orderItemResponse.setQuantity(orderItem.getQuantity());
        return orderItemResponse;
    }



}

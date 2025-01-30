package com.hariSolution.mapper;

import com.hariSolution.order.Order;
import com.hariSolution.order.OrderRequest;
import com.hariSolution.order.OrderResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OrderMapper {

    public Order toOrder(OrderRequest request) {

        Order order = new Order();
        order.setReference(request.getReference());
        order.setPaymentMethod(request.getPaymentMethod());
        order.setUserId(request.getUserId());
        order.setTotalAmount(request.getTotalAmount());

        return order;
    }

    public OrderResponse toOrderResponse(Order order) {
        if (order == null) {
            return new OrderResponse();
        }

        OrderResponse response = new OrderResponse();
        response.setReference(order.getReference());
        response.setAmount(order.getTotalAmount());
        response.setPaymentMethod(order.getPaymentMethod());
        response.setUserId(order.getUserId());

        return response;
    }


}

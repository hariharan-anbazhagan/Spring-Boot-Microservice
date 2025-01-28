package com.hariSolution.Order;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class OrderMapper {

    Order toOrder(OrderRequest request){
        if (request == null) {
            return null;
        }
        Order order=new Order();
        order.setReference(request.getReference());
        order.setPaymentMethod(request.getPaymentMethod());
        order.setUserId(request.getUserId());
        order.setTotalAmount(request.getAmount());
        order.setCreatedDate(LocalDateTime.now());
        order.setLastModifiedDate(LocalDateTime.now());
        return order;

    }

    OrderResponse toOrderResponse(Order order){
        if (order == null) {
            return null;
        }
        OrderResponse response=new OrderResponse();
        response.setId(order.getId());
        response.setReference(order.getReference());
        response.setPaymentMethod(order.getPaymentMethod());
        response.setUserId(order.getUserId());
        response.setAmount(order.getTotalAmount());
        return response;

    }
}

package com.hariSolution.controller;

import com.hariSolution.Order.OrderRequest;
import com.hariSolution.Order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/click-order")
    public ResponseEntity<Integer> createOrder(@RequestBody OrderRequest request){
        return ResponseEntity.ok(this.orderService.createOrder(request));
    }


}

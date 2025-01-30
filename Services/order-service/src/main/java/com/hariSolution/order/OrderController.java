package com.hariSolution.order;

import lombok.RequiredArgsConstructor;
import org.hibernate.query.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/click-order")
    public ResponseEntity<OrderDetailsResponse> createOrder(@RequestBody OrderRequest request){
        OrderDetailsResponse response=this.orderService.createOrder(request);
        return ResponseEntity.ok(response);

    }

    @PostMapping("/get-allOrder")
    public ResponseEntity<OrderDetailsResponse> getAllOrderDetails(){
        OrderDetailsResponse response=this.orderService.getAllOrderDetails();
        return ResponseEntity.ok(response);
    }
}

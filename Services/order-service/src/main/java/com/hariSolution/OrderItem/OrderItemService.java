package com.hariSolution.OrderItem;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;

    public void createOrderItem(OrderItemRequest request){
        OrderItem orderItem=this.orderItemRepository.save(this.orderItemMapper.toOrderItem(request));
        this.orderItemMapper.toOrderItemResponse(orderItem);

    }
}

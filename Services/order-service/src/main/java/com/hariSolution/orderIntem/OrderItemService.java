package com.hariSolution.orderIntem;

import com.hariSolution.mapper.OrderItemMapper;
import com.hariSolution.mapper.OrderItemResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;
    private final OrderItemResponseMapper responseMapper;

    public void createOrderItem(OrderItemRequest request){
        this.orderItemRepository.save(this.orderItemMapper.toOrderItem(request));
    }


}

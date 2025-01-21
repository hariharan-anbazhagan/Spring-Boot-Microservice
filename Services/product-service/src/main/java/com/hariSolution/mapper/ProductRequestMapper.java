package com.hariSolution.mapper;

import com.hariSolution.model.ProductDto;
import com.hariSolution.model.ProductRequest;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductRequestMapper {
    public ProductDto toProductDto(ProductRequest request) {
        if (request == null || request.getData() == null) {
            throw new IllegalArgumentException("ProductRequest or its data cannot be null");
        }
        return request.getData();
    }
}

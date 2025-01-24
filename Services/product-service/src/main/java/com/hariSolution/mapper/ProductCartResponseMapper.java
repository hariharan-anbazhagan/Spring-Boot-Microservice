package com.hariSolution.mapper;

import com.hariSolution.model.Product;
import com.hariSolution.model.ProductCartResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductCartResponseMapper {

    public ProductCartResponse toResponse(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        ProductCartResponse response = new ProductCartResponse();
        response.setProductId(product.getId());
        response.setName(product.getName());
        response.setDescriptions(product.getDescriptions());
        response.setIsAvailable(product.getIsAvailable());
        response.setAvailableQuantity(product.getAvailableQuantity());
        response.setPrice(product.getPrice());

        if (product.getCategory() != null) {
            response.setCategoryName(product.getCategory().getName());
        } else {
            response.setCategoryName(null);
        }

        return response;
    }

}

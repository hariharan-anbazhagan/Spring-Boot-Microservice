package com.hariSolution.mapper;

import com.hariSolution.model.Product;
import com.hariSolution.model.ProductPurchaseResponse;
import jakarta.persistence.Column;
import org.springframework.stereotype.Component;

@Component
public class PurchaseProductsMapper {
    public ProductPurchaseResponse toProductPurchaseResponse(Product product,Integer quantity){
        ProductPurchaseResponse response=new ProductPurchaseResponse();
        response.setProductId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescriptions());
        response.setPrice(product.getPrice());
        response.setQuantity(quantity);
        return response;

    }
}

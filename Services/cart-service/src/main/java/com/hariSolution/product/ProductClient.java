package com.hariSolution.product;

import com.hariSolution.model.ProductDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "product-service",
        url = "${application.config.product-url}"
)
public interface ProductClient {

    @GetMapping(value = "/get/{product-id}")
    ProductDetails getProductFromProductService(@PathVariable("product-id") Integer productId);
}

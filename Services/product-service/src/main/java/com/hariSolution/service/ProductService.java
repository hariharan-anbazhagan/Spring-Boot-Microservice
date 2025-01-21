package com.hariSolution.service;

import com.hariSolution.mapper.ProductMapper;
import com.hariSolution.mapper.ProductRequestMapper;
import com.hariSolution.mapper.ProductResponseMapper;
import com.hariSolution.model.Product;
import com.hariSolution.model.ProductDto;
import com.hariSolution.model.ProductRequest;
import com.hariSolution.model.ProductResponse;
import com.hariSolution.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapperService;
    private final ProductResponseMapper productResponseMapper;
    private final ProductRequestMapper productRequestService;
    public ProductResponse createProductDetails( ProductRequest productRequest) {

        // Convert ProductRequest to ProductDto
        ProductDto productDto = this.productRequestService.toProductDto(productRequest);

        // Save the product entity
        var product = this.productRepository.save(productMapperService.toEntity(productDto));

        // Prepare response data
        LinkedHashMap<String, Object> data = new LinkedHashMap<>();
        data.put("productDetails:"+product.getId(), productDto);

        // Create and return the response
        return this.productResponseMapper.createResponse(
                data,
                "Product details successfully created",
                HttpStatus.OK.value(),
                HttpStatus.OK
        );






    }
}

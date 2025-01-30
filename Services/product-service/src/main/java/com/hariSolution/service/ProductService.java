package com.hariSolution.service;

import com.hariSolution.exception.ProductNotFoundException;
import com.hariSolution.mapper.*;
import com.hariSolution.model.*;
import com.hariSolution.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapperService;
    private final ProductResponseMapper productResponseMapper;
    private final ProductRequestMapper productRequestService;
    private final ProductCartResponseMapper productCartService;
    private final PurchaseProductsMapper purchaseMapper;

    public ProductResponse createProductDetails(ProductRequest productRequest) {

        // Convert ProductRequest to ProductDto
        ProductDto productDto = this.productRequestService.toProductDto(productRequest);

        // Save the product entity
        var product = this.productRepository.save(productMapperService.toEntity(productDto));

        // Prepare response data
        LinkedHashMap<String, Object> data = new LinkedHashMap<>();
        data.put("productDetails:" + product.getId(), productDto);

        // Create and return the response
        return this.productResponseMapper.createResponse(
                data,
                "Product details successfully created",
                HttpStatus.OK.value(),
                HttpStatus.OK
        );


    }

    public ProductResponse getAllProductDetails() {

        List<Product> products = this.productRepository.findAll();


        LinkedHashMap<String, Object> data = new LinkedHashMap<>();


        products.stream()
                .sorted(Comparator.comparing(Product::getId))
                .forEach(product -> {
                    ProductDto productDto = this.productMapperService.toDto(product);
                    data.put("Product ID: " + product.getId(), productDto);
                });

        return this.productResponseMapper.createResponse(
                data,
                "Successfully fetched all products!",
                HttpStatus.OK.value(),
                HttpStatus.OK
        );
    }

    public ProductResponse getProductById(Integer productId) {

        Product product = this.productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + productId + " not found"));


        ProductDto productDto = this.productMapperService.toDto(product);


        LinkedHashMap<String, Object> data = new LinkedHashMap<>();
        data.put("productDetail", productDto);


        return this.productResponseMapper.createResponse(
                data,
                "Successfully fetched the requested product!",
                HttpStatus.OK.value(),
                HttpStatus.OK
        );
    }

    public ProductResponse updatedProductDetails(Integer productId, @Valid ProductRequest productRequest) {

        Product product = this.productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + productId + " not found"));

        ProductDto productDto = this.productRequestService.toProductDto(productRequest);

        this.ProductMarge(product, productDto);


        LinkedHashMap<String, Object> data = new LinkedHashMap<>();
        data.put("productDetails:" + product.getId(), productDto);

        return this.productResponseMapper.createResponse(
                data,
                "Product details successfully updated",
                HttpStatus.OK.value(),
                HttpStatus.OK
        );

    }

    private void ProductMarge(Product product, ProductDto productDto) {
        if (StringUtils.isNotBlank(productDto.getName())) {
            product.setName(productDto.getName());
        }
        if (StringUtils.isNotBlank(productDto.getDescriptions())) {
            product.setDescriptions(productDto.getDescriptions());
        }
        if (productDto.getIsAvailable() != null) {
            product.setIsAvailable(productDto.getIsAvailable());
        }
        if (productDto.getPrice() != null) {
            product.setPrice(productDto.getPrice());
        }
        this.productRepository.save(product);
    }

    public ProductCartResponse findProductById(Integer productId) {

        if (productId == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }


        Product product = this.productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + productId + " not found"));

        return this.productCartService.toResponse(product);
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> requests) {

        var productIds = requests.stream().map(ProductPurchaseRequest::getProductId).toList();

        var storedProducts = this.productRepository.findAllByIdInOrderById(productIds);

        if (productIds.size() != storedProducts.size()) {
            throw new RuntimeException("One or more products does not exist");
        }
        var sortedRequest = requests
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::getProductId))
                .toList();

        List<ProductPurchaseResponse> PurchaseProducts = new ArrayList<>();

        for (int i = 0; i < storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var productRequest = sortedRequest.get(i);

            if (product.getAvailableQuantity() < productRequest.getQuantity()) {
                throw new RuntimeException("Insufficient stock quantity for product with ID:: " + productRequest.getProductId());
            }
            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.getQuantity();

            product.setAvailableQuantity(newAvailableQuantity);

            this.productRepository.save(product);
            PurchaseProducts.add(this.purchaseMapper.toProductPurchaseResponse(product, productRequest.getProductId()));


        }

        return PurchaseProducts;
    }

    public ProductPurchaseResponse ProductPurchase(ProductPurchaseRequest request) {

        var product = this.productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product with ID " + request.getProductId() + " not found."));


        if (product.getAvailableQuantity() < request.getQuantity()) {
            throw new RuntimeException("Insufficient stock quantity for product with ID: " + product.getId());
        }

        var newAvailableQuantity = product.getAvailableQuantity() - request.getQuantity();
        product.setAvailableQuantity(newAvailableQuantity);



        Product updatedProduct = this.productRepository.save(product);

        return this.purchaseMapper.toProductPurchaseResponse(updatedProduct, request.getQuantity());
    }

}

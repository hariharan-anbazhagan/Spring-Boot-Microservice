package com.hariSolution.controller;

import com.hariSolution.model.*;

import com.hariSolution.service.CategoryService;
import com.hariSolution.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@CrossOrigin
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @PostMapping("/create-category")
    public ResponseEntity<CategoryResponse>createCategoryDetails(@RequestBody @Valid CategoryDto categoryDto){

        CategoryResponse response=this.categoryService.createCategoryDetails(categoryDto);

        return ResponseEntity.ok(response);

    }

    @GetMapping("/get-allCategory")
    public ResponseEntity<ProductResponse>getAllCategoryInformation(){
        ProductResponse productResponse=this.categoryService.getAllCategoryInformation();
        return ResponseEntity.ok(productResponse);
    }
    @PostMapping("/create-product")
    public ResponseEntity<ProductResponse> createProductDetails(@RequestBody @Valid ProductRequest productRequest){
        ProductResponse response=this.productService.createProductDetails(productRequest);
        return ResponseEntity.ok(response);

    }

    @GetMapping("/get-allProduct")
    public ResponseEntity<ProductResponse> getAllProductDetails(){
        ProductResponse response=this.productService.getAllProductDetails();
        return ResponseEntity.ok(response);

    }
    @GetMapping("/get-product/{product-id}")
    public  ResponseEntity<ProductResponse> getProductById(@PathVariable(value = "product-id") Integer productId){
        ProductResponse response=this.productService.getProductById(productId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/update-product/{product-id}")
    public ResponseEntity<ProductResponse> updatedProductDetails(@PathVariable(value = "product-id") Integer productId,
                                                                 @RequestBody @Valid ProductRequest productRequest){
        ProductResponse response=this.productService.updatedProductDetails(productId,productRequest);

        return ResponseEntity.ok(response);

    }

    @GetMapping(value = "/get/{product-id}")
    public ResponseEntity<ProductCartResponse>  findProductById(@PathVariable(value = "product-id") Integer productId){
        ProductCartResponse response=this.productService.findProductById(productId);
        return ResponseEntity.ok(response);

    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>>purchaseProducts(@RequestBody List<ProductPurchaseRequest> requests){

        return ResponseEntity.ok(productService.purchaseProducts(requests));

    }
    @PostMapping("/purchase-product")
    public ResponseEntity<ProductPurchaseResponse> ProductPurchase(@RequestBody ProductPurchaseRequest request){

        ProductPurchaseResponse response= this.productService.ProductPurchase(request);

        return ResponseEntity.ok(response);
    }






}

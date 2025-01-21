package com.hariSolution.controller;

import com.hariSolution.model.CategoryDto;
import com.hariSolution.model.CategoryResponse;
import com.hariSolution.model.ProductRequest;
import com.hariSolution.model.ProductResponse;
import com.hariSolution.repository.CategoryRepository;
import com.hariSolution.repository.ProductRepository;
import com.hariSolution.service.CategoryService;
import com.hariSolution.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
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




}

package com.hariSolution.mapper;

import com.hariSolution.model.Product;
import com.hariSolution.model.ProductDto;
import com.hariSolution.repository.CategoryRepository;
import com.hariSolution.model.Category;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final CategoryRepository categoryRepository;


    public Product toEntity(ProductDto productDto) {

        if (productDto == null) {
            throw new IllegalArgumentException("ProductDto cannot be null");
        }

        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescriptions(productDto.getDescriptions());
        product.setPrice(productDto.getPrice());
        product.setIsAvailable(productDto.getIsAvailable());


        if (StringUtils.isNotBlank(productDto.getCategoryName())) {
            Category category = this.categoryRepository.findByName(productDto.getCategoryName());
            if (category == null) {
                throw new IllegalArgumentException("Category with name '" + productDto.getCategoryName() + "' not found");
            }
            product.setCategory(category);
        }

        product.setCreatedDate(LocalDateTime.now());
        product.setLastModifiedDate(LocalDateTime.now());
        return product;
    }

    public ProductDto toDto(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setDescriptions(product.getDescriptions());
        productDto.setIsAvailable(product.getIsAvailable());
        productDto.setPrice(product.getPrice());

        if (product.getCategory() != null) {
            productDto.setCategoryName(product.getCategory().getName());
        } else {
            productDto.setCategoryName(null);
        }

        return productDto;
    }





}

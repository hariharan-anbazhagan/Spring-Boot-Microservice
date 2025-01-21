package com.hariSolution.service;

import com.hariSolution.mapper.CategoryMapper;
import com.hariSolution.mapper.CategoryResponseMapper;
import com.hariSolution.mapper.ProductResponseMapper;
import com.hariSolution.model.Category;
import com.hariSolution.model.CategoryDto;
import com.hariSolution.model.CategoryResponse;
import com.hariSolution.model.ProductResponse;
import com.hariSolution.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final CategoryResponseMapper categoryResponseService;
    private final ProductResponseMapper productResponseService;

    public CategoryResponse createCategoryDetails(CategoryDto categoryDto) {

        Category category = this.categoryRepository.save(categoryMapper.toEntity(categoryDto));

        return categoryResponseService.createResponse(
                "Category has been created",
                category.getName() + ", related category has been successfully created",
                HttpStatus.OK
        );
    }

    public ProductResponse getAllCategoryInformation() {
        List<Category> categoryList = this.categoryRepository.findAll();
        LinkedHashMap<String, Object> data = new LinkedHashMap<>();

        categoryList.forEach(category -> {
            CategoryDto categoryDto = this.categoryMapper.toDto(category);
            data.put("Category Details:", categoryDto);
        });

        return this.productResponseService.createResponse(data, "Successfully category list has been fetched!!!", HttpStatus.OK.value(), HttpStatus.OK);


    }
}

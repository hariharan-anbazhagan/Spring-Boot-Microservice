package com.hariSolution.mapper;

import com.hariSolution.model.Category;
import com.hariSolution.model.CategoryDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toEntity(CategoryDto categoryDto);

    @InheritInverseConfiguration
    CategoryDto toDto(Category category);
}

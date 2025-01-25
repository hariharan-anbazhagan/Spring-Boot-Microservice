package com.hariSolution.mapper;

import com.hariSolution.model.ActivityType;
import com.hariSolution.model.ProductStock;
import com.hariSolution.model.ProductStockDto;
import org.springframework.stereotype.Component;

@Component
public class StockMapper {


    public ProductStock toEntity(ProductStockDto dto) {
        if (dto == null) {
            return null;
        }
        ProductStock productStock = new ProductStock();

        productStock.setProductId(dto.getProductId());
        productStock.setProductName(dto.getProductName());
        productStock.setStockQuantity(dto.getStockQuantity());
        productStock.setProductAvailableQuantity(dto.getProductAvailableQuantity());
        productStock.setCategoryName(dto.getCategoryName());


        if (dto.getActivityType() != null) {

            try {
                productStock.setActivityType(ActivityType.valueOf(dto.getActivityType()));
            } catch (IllegalArgumentException e) {
                productStock.setActivityType(ActivityType.NEW_STOCK_ADD);
            }
        }

        productStock.setMinimumThreshold(dto.getMinimumThreshold());
        return productStock;
    }

    public ProductStockDto toDto(ProductStock entity) {
        if (entity == null) {
            return null;
        }
        ProductStockDto productStockDto = new ProductStockDto();

        productStockDto.setProductId(entity.getProductId());
        productStockDto.setProductName(entity.getProductName());
        productStockDto.setStockQuantity(entity.getStockQuantity());
        productStockDto.setProductAvailableQuantity(entity.getProductAvailableQuantity());
        productStockDto.setCategoryName(entity.getCategoryName());

        if (entity.getActivityType() != null) {

            productStockDto.setActivityType(entity.getActivityType().name());
        }

        productStockDto.setMinimumThreshold(entity.getMinimumThreshold());
        return productStockDto;
    }
}

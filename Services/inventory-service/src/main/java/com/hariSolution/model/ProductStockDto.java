package com.hariSolution.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.springframework.context.annotation.Configuration;

import java.io.Serial;
import java.io.Serializable;

/**
 * DTO class for Product Stock details.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Configuration
@JsonSerialize // Optional: Can be removed if no custom serialization is required.
public class ProductStockDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer productId;
    private String productName;
    private Integer ProductAvailableQuantity;
    private String categoryName;
    private Integer stockQuantity;
    private String activityType;
    private Integer minimumThreshold;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductAvailableQuantity() {
        return ProductAvailableQuantity;
    }

    public void setProductAvailableQuantity(Integer productAvailableQuantity) {
        ProductAvailableQuantity = productAvailableQuantity;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public Integer getMinimumThreshold() {
        return minimumThreshold;
    }

    public void setMinimumThreshold(Integer minimumThreshold) {
        this.minimumThreshold = minimumThreshold;
    }
}

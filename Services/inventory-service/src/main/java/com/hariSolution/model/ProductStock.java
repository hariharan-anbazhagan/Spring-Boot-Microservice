package com.hariSolution.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "product_Stock_details")
@Component
@JsonSerialize

@NamedQueries({
        @NamedQuery(
                name = "ProductStock.findByStockQuantityGreaterThanOrEqual",
                query = "SELECT a FROM ProductStock a WHERE a.stockQuantity >= :stockQuantity"
        ),
        @NamedQuery(
                name = "ProductStock.findByStockQuantityLessThanOrEqual",
                query = "SELECT a FROM ProductStock a WHERE a.stockQuantity <= :stockQuantity"
        )
})

@EntityListeners(AuditingEntityListener.class)
public class ProductStock implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_id", nullable = false)
    private Integer productId;

    @Column(name = "name", nullable = false, length = 255)
    private String productName;
    @Column(name = "product_available_quantity", nullable = false)
    private Integer ProductAvailableQuantity;
    @Column(name = "category_name", nullable = false, length = 255)
    private String categoryName;

    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "activity_type") // Optional, if you want to explicitly map the column name
    private ActivityType activityType;

    @Column(name = "minimum_threshold", nullable = false)
    private Integer minimumThreshold;

    @LastModifiedDate
    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public Integer getMinimumThreshold() {
        return minimumThreshold;
    }

    public void setMinimumThreshold(Integer minimumThreshold) {
        this.minimumThreshold = minimumThreshold;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}

package com.hariSolution.repository;

import com.hariSolution.model.ProductStock;
import jakarta.persistence.TypedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<ProductStock,Integer> {
    ProductStock findByProductId(Integer productId);

   Optional<ProductStock> findAllByProductNameIgnoreCase(String productName);

   Optional<ProductStock>findByStockQuantityGreaterThanOrEqual(@Param("stockQuantity") Integer stockQuantity);

    Optional<ProductStock>findByStockQuantityLessThanOrEqual(@Param("stockQuantity") Integer stockQuantity);


}

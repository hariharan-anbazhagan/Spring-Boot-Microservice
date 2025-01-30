package com.hariSolution.repository;

import com.hariSolution.model.Product;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findAllByIdInOrderById(List<Integer> productIds);

    
}

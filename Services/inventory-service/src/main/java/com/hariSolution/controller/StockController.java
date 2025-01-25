package com.hariSolution.controller;

import com.hariSolution.model.StockResponse;
import com.hariSolution.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping("/create-stock")
    public ResponseEntity<StockResponse> createStock(
            @RequestParam(value = "productId") Integer productId,
            @RequestParam(value = "stockQuantity") Integer stockQuantity,
            @RequestParam(value = "activityType") String activityType
    ) {
        StockResponse response = this.stockService.createStock(productId, stockQuantity, activityType);

        return ResponseEntity.ok(response);

    }

    @PutMapping("/update-stock")
    public ResponseEntity<StockResponse> updatedExistsProductStock(
            @RequestParam(value = "productId") Integer productId,
            @RequestParam(value = "stockQuantity") Integer stockQuantity

    ) {
        StockResponse response = this.stockService.updatedExistsProductStock(productId, stockQuantity);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-allStock")
    public ResponseEntity<StockResponse> getAllStockDetails(){
        StockResponse response=this.stockService.getAllStockDetails();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/get-stock/{stock-id}")
    public ResponseEntity<StockResponse> getStockDetailsById(
            @PathVariable(value = "stock-id") Integer stockId){
        StockResponse response=this.stockService.getStockDetailsById(stockId);
        return ResponseEntity.ok(response);

    }

    @GetMapping("/get-stuck/{productName}")
    public ResponseEntity<StockResponse> getStockDetailsByProductName(
            @PathVariable(value = "productName") String productName){
        StockResponse response=this.stockService.getStockDetailsByProductName(productName);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/get-greaterThan")
    public ResponseEntity<StockResponse> getStockDetailsGreaterThanEqualToStockQuantity (
            @RequestParam(value = "stockQuantity") Integer stockQuantity){
        StockResponse response=this.stockService.getStockDetailsGreaterThanEqualToStockQuantity(stockQuantity);
        return ResponseEntity.ok(response);
    }



}

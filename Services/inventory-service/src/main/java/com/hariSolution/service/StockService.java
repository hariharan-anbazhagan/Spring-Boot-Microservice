package com.hariSolution.service;

import com.hariSolution.exception.ProductNotFoundException;
import com.hariSolution.mapper.StockMapper;
import com.hariSolution.mapper.StockResponseMapper;
import com.hariSolution.model.ActivityType;
import com.hariSolution.model.ProductStock;
import com.hariSolution.model.ProductStockDto;
import com.hariSolution.model.StockResponse;
import com.hariSolution.product.ProductDetails;
import com.hariSolution.product.ProductRestClient;
import com.hariSolution.repository.StockRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing product stock.
 */
@Service
@Transactional
public class StockService {

    private final StockRepository stockRepository;
    private final ProductRestClient productRestClient;
    private final StockMapper stockMapper;
    private final StockResponseMapper stockResponseMapper;

    public StockService(StockRepository stockRepository, ProductRestClient productRestClient, StockMapper stockMapper, StockResponseMapper stockResponseMapper) {
        this.stockRepository = stockRepository;
        this.productRestClient = productRestClient;
        this.stockMapper = stockMapper;
        this.stockResponseMapper = stockResponseMapper;
    }

    public StockResponse createStock(Integer productId, Integer stockQuantity, String activityType) {


        ProductDetails productDetails = productRestClient.getProductFromProductService(productId);

        ProductStock existingStock = stockRepository.findByProductId(productId);

        LinkedHashMap<String, Object> responseData = new LinkedHashMap<>();

        if (existingStock != null) {

            ProductStockDto existingStockDto = stockMapper.toDto(existingStock);

            responseData.put("Product already exists", existingStockDto);
            return stockResponseMapper.createResponse(
                    responseData,
                    "Product already available in stock",
                    HttpStatus.CONFLICT.value(),
                    HttpStatus.CONFLICT
            );
        }

        System.out.println(productDetails);

        ProductStock productStock = new ProductStock();
        productStock.setProductId(productDetails.getProductId());
        productStock.setProductName(productDetails.getName());
        productStock.setCategoryName(productDetails.getCategoryName());
        productStock.setProductAvailableQuantity(productDetails.getAvailableQuantity());
        productStock.setMinimumThreshold(500);
        productStock.setStockQuantity(stockQuantity);
        productStock.setActivityType(ActivityType.valueOf(activityType));
        productStock.setLastUpdated(LocalDateTime.now());

        ProductStock savedStock = stockRepository.save(productStock);

        ProductStockDto savedStockDto = stockMapper.toDto(savedStock);

        responseData.put("Stock ID: " + savedStock.getId(), savedStockDto);

        return stockResponseMapper.createResponse(
                responseData,
                "Stock successfully updated",
                HttpStatus.OK.value(),
                HttpStatus.OK);
    }

    public StockResponse updatedExistsProductStock(Integer productId, Integer stockQuantity) {


        ProductStock stock = this.stockRepository.findByProductId(productId);

        if (productId == null || !productId.equals(stock.getProductId())) {

            return stockResponseMapper.createResponse(
                    null,
                    "Product not found for ID: " + productId,
                    HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST
            );
        }


        LinkedHashMap<String, Object> responseData = new LinkedHashMap<>();

        stock.setStockQuantity(stockQuantity);
        stock.setActivityType(ActivityType.STOCK_UPDATED);

        ProductStock savedStock = this.stockRepository.save(stock);

        responseData.put("Stock ID: " + savedStock.getId(), savedStock);

        return stockResponseMapper.createResponse(
                responseData,
                "Stock successfully updated",
                HttpStatus.OK.value(),
                HttpStatus.OK);


    }


    public StockResponse getAllStockDetails() {

        List<ProductStock> stocks=this.stockRepository.findAll();

        LinkedHashMap<String, Object> responseData = new LinkedHashMap<>();

        stocks.forEach(stock->{
            ProductStockDto stockDto=this.stockMapper.toDto(stock);
            responseData.put("Stock Id:"+stock.getId(),stockDto);
        });

        return this.stockResponseMapper
                .createResponse(responseData,"Successfully stock fetched!!",HttpStatus.OK.value(),HttpStatus.OK);

    }

    public StockResponse getStockDetailsById(Integer stockId) {
        ProductStock stock=this.stockRepository.findById(stockId)
                .orElseThrow(()->new ProductNotFoundException("product id not found"));

        ProductStockDto stockDto = this.stockMapper.toDto(stock);

        LinkedHashMap<String, Object> responseData = new LinkedHashMap<>();
        responseData.put("Stock Id: " + stock.getId(), stockDto);

        return this.stockResponseMapper.createResponse(
                responseData,
                "Successfully fetched stock details!",
                HttpStatus.OK.value(),
                HttpStatus.OK
        );
    }

    public StockResponse getStockDetailsByProductName(String productName) {

        ProductStock stock=this.stockRepository.findAllByProductNameIgnoreCase(productName)
                .orElseThrow(()->new ProductNotFoundException("product id not found"));

        ProductStockDto stockDto = this.stockMapper.toDto(stock);

        LinkedHashMap<String, Object> responseData = new LinkedHashMap<>();
        responseData.put("Stock Id: " + stock.getId(), stockDto);

        return this.stockResponseMapper.createResponse(
                responseData,
                "Successfully fetched stock details!",
                HttpStatus.OK.value(),
                HttpStatus.OK
        );
    }

    public StockResponse getStockDetailsGreaterThanEqualToStockQuantity(Integer stockQuantity) {

        ProductStock stock=this.stockRepository.findByStockQuantityGreaterThanOrEqual(stockQuantity)
                .orElseThrow(()->new ProductNotFoundException("product id not found"));

        ProductStockDto stockDto = this.stockMapper.toDto(stock);

        LinkedHashMap<String, Object> responseData = new LinkedHashMap<>();
        responseData.put("Stock Id: " + stock.getId(), stockDto);

        return this.stockResponseMapper.createResponse(
                responseData,
                "Successfully fetched stock details!",
                HttpStatus.OK.value(),
                HttpStatus.OK
        );


    }
}

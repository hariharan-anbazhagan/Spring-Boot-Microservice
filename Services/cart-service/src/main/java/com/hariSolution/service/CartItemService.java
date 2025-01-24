package com.hariSolution.service;

import com.hariSolution.Mapper.CartItemMapper;
import com.hariSolution.model.CartItem;
import com.hariSolution.model.ProductDetails;
import com.hariSolution.product.ProductClient;
import com.hariSolution.repository.CartItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Transactional
public class CartItemService {

    private final ProductClient productData;
    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;

    private static final Logger logger = LoggerFactory.getLogger(CartItemService.class);

    public ProductDetails createCartItemDetails(Integer productId, Integer quantity) {
        logger.info("Initiating cart item creation for productId: {} and quantity: {}", productId, quantity);


        ProductDetails productDetails = productData.getProductFromProductService(productId);

        if (productDetails == null) {
            logger.error("Product not found for productId: {}", productId);
            throw new ProductNotFoundException("Product not found for ID: " + productId);
        }

        logger.info("Product details retrieved: {}", productDetails);

        // Populate the CartItem
        CartItem cartItem = new CartItem();
        cartItem.setProductId(productDetails.getProductId());
        cartItem.setName(productDetails.getName());
        cartItem.setIsAvailable(productDetails.getIsAvailable());
        cartItem.setCategoryName(productDetails.getCategoryName());
        cartItem.setPrice(productDetails.getPrice());
        cartItem.setQuantity(quantity);
        cartItem.setSubTotal(productDetails.getPrice().multiply(BigDecimal.valueOf(quantity)));
       // Link the cart to this CartItem

        logger.info("CartItem populated: {}", cartItem);

        try {

            CartItem savedCartItem = cartItemRepository.save(cartItem);
            logger.info("CartItem saved successfully: {}", savedCartItem);


            return cartItemMapper.toProductDetails(savedCartItem);

        } catch (Exception e) {
            logger.error("Error saving CartItem for productId: {}", productId, e);
            throw new RuntimeException("Error saving CartItem", e);  // Optionally define a custom exception for CartItem saving
        }
    }
}

package com.hariSolution.controller;

import com.hariSolution.model.CartResponse;
import com.hariSolution.model.ProductDetails;
import com.hariSolution.service.CartItemService;

import com.hariSolution.service.CartService;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartItemService cartItemService;
    private final CartService cartService;
    private static final Logger logger = LoggerFactory.getLogger(CartController.class);


    @PostMapping("/add")
    public ResponseEntity<ProductDetails> createCartItemDetails(
            @RequestParam("productId") Integer productId,
            @RequestParam("quantity") Integer quantity
    ) {
        logger.info("Received request to add CartItem: productId={}, quantity={}", productId, quantity);

        ProductDetails productDetails = cartItemService.createCartItemDetails(productId, quantity);
        return ResponseEntity.ok(productDetails);

    }

    @GetMapping("/getCart")
    public ResponseEntity<CartResponse> getCart() {

        CartResponse cartResponse = this.cartService.getCart();

        return ResponseEntity.ok(cartResponse);

    }


}




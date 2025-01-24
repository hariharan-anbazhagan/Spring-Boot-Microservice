package com.hariSolution.Mapper;

import com.hariSolution.model.*;
import com.hariSolution.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CartMapper {
    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;

    public CartResponse toCartResponse(Cart cart) {
        if (cart == null) {
            return null;
        }

        CartResponse cartResponse = new CartResponse();

        // Convert CartItems to CartItemResponses
      /*  List<CartItem> cartItems = cart.getCartItems();
        List<CartItemResponse> cartItemResponses = (cartItems != null) ?
                cartItems.stream()
                        .map(this::toCartItemResponse)
                        .collect(Collectors.toList())
                : Collections.emptyList();*/
        List<CartItem> cartItems = this.cartItemRepository.findAll();
        List<ProductDetails> cartList = new ArrayList<>();
        cartItems.forEach(cartItem -> {
            ProductDetails productDetails = this.cartItemMapper.toProductDetails(cartItem);
            cartList.add(productDetails);
        });

        System.out.println(cartList);
        cartResponse.setCartItems(cartList);
        cartResponse.setTotalPrice(cart.getTotalPrice());
        cartResponse.setIsCheckedOut(cart.getIsCheckedOut());

        return cartResponse;
    }

    private CartItemResponse toCartItemResponse(CartItem item) {
        if (item == null) {
            return null;
        }

        CartItemResponse response = new CartItemResponse();
        response.setProductId(item.getProductId());
        response.setName(item.getName());
        response.setPrice(item.getPrice());
        response.setQuantity(item.getQuantity());
        response.setSubTotal(item.getSubTotal());
        return response;
    }
}

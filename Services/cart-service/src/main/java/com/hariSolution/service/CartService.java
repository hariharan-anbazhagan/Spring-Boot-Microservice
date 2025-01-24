package com.hariSolution.service;

import com.hariSolution.Mapper.CartItemMapper;
import com.hariSolution.Mapper.CartMapper;
import com.hariSolution.model.Cart;
import com.hariSolution.model.CartItem;
import com.hariSolution.model.CartResponse;
import com.hariSolution.model.ProductDetails;
import com.hariSolution.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {


    private final CartMapper cartMapperService;
    private final CartItemRepository cartItemRepository;
    private static final Logger logger = LoggerFactory.getLogger(CartService.class);
    private final CartItemMapper cartItemMapper;

    @Transactional
    public CartResponse getCart() {

        Cart cart = new Cart();

        List<CartItem> cartItems = cartItemRepository.findAll();
        logger.info("Cart items for : {}", cartItems);
        cartItems.forEach(cartItem -> {
            ProductDetails productDetails = this.cartItemMapper.toProductDetails(cartItem);
        });

        BigDecimal totalPrice = cartItems.stream()
                .map(CartItem::getSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        cart.setTotalPrice(totalPrice);

        return cartMapperService.toCartResponse(cart);
    }
}

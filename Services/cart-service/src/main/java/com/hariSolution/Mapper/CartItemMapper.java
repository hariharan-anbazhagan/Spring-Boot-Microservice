package com.hariSolution.Mapper;

import com.hariSolution.model.CartItem;
import com.hariSolution.model.ProductDetails;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    CartItem toCartItem(ProductDetails productDetails);

    @InheritInverseConfiguration
    ProductDetails toProductDetails(CartItem cartItem);

}

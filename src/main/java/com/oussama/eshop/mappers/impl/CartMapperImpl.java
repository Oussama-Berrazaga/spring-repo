package com.oussama.eshop.mappers.impl;

import com.oussama.eshop.domain.dto.CartDto;
import com.oussama.eshop.domain.entities.Cart;
import com.oussama.eshop.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CartMapperImpl implements Mapper<Cart, CartDto> {

    private final ModelMapper modelMapper;

    public CartMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CartDto mapTo(Cart cart) {
        return modelMapper.map(cart,CartDto.class);
    }

    @Override
    public Cart mapFrom(CartDto cartDto) {
        return modelMapper.map(cartDto,Cart.class);
    }
}

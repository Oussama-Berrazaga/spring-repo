package com.oussama.eshop.mappers.impl;

import com.oussama.eshop.domain.dto.CartProductDto;
import com.oussama.eshop.domain.entities.CartProduct;
import com.oussama.eshop.mappers.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CartProductMapperImpl implements Mapper<CartProduct, CartProductDto> {

    private final ModelMapper modelMapper;


    @Override
    public CartProductDto mapTo(CartProduct cartProduct) {
        return modelMapper.map(cartProduct, CartProductDto.class);
    }

    @Override
    public CartProduct mapFrom(CartProductDto cartProductDto) {
        return modelMapper.map(cartProductDto,CartProduct.class);
    }
}

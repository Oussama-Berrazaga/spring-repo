package com.oussama.eshop.mappers.impl;

import com.oussama.eshop.domain.dto.OrderDto;
import com.oussama.eshop.domain.entities.Order;
import com.oussama.eshop.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderMapperImpl implements Mapper<Order, OrderDto> {
    private final ModelMapper modelMapper;

    public OrderMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public OrderDto mapTo(Order order) {
        return modelMapper.map(order, OrderDto.class);
    }

    @Override
    public Order mapFrom(OrderDto orderDto) {
        return modelMapper.map(orderDto, Order.class);
    }
}

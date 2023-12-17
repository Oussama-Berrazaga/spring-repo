package com.oussama.eshop.mappers.impl;

import com.oussama.eshop.domain.dto.TokenDto;
import com.oussama.eshop.domain.entities.Token;
import com.oussama.eshop.mappers.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@RequiredArgsConstructor
public class TokenMapperImpl implements Mapper<Token, TokenDto> {

    private final ModelMapper modelMapper;

    @Override
    public TokenDto mapTo(Token token) {
        return modelMapper.map(token,TokenDto.class);
    }

    @Override
    public Token mapFrom(TokenDto tokenDto) {
        return modelMapper.map(tokenDto, Token.class);
    }
}
package com.oussama.eshop.mappers.impl;

import com.oussama.eshop.domain.dto.AdminDto;
import com.oussama.eshop.domain.entities.Admin;
import com.oussama.eshop.mappers.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AdminMapperImpl implements Mapper<Admin, AdminDto> {

    private final ModelMapper modelMapper;

    @Override
    public AdminDto mapTo(Admin admin) {
        return modelMapper.map(admin,AdminDto.class);
    }

    @Override
    public Admin mapFrom(AdminDto adminDto) {
        return modelMapper.map(adminDto,Admin.class);
    }
}

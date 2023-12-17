package com.oussama.eshop.controllers.responses;

import com.oussama.eshop.domain.dto.CustomerDto;
import com.oussama.eshop.domain.dto.UserDto;
import lombok.Builder;

@Builder
public record AuthRes(UserDto user,String token) {
}

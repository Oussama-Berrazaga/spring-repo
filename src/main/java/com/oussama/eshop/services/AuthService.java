package com.oussama.eshop.services;

import com.oussama.eshop.controllers.requests.AuthReq;
import com.oussama.eshop.controllers.requests.RegisterReq;
import com.oussama.eshop.controllers.responses.AuthRes;

public interface AuthService {

    AuthRes register(RegisterReq request);
    AuthRes authenticate(AuthReq req);
}

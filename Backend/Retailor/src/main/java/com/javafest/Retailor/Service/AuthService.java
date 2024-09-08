package com.javafest.Retailor.Service;

import com.javafest.Retailor.Dto.AuthenticationResponse;
import com.javafest.Retailor.Dto.LoginReq;
import com.javafest.Retailor.Entity.Users;

public interface AuthService {
    public Users save(Users user);
    public AuthenticationResponse findByUsername(LoginReq loginReq);
}

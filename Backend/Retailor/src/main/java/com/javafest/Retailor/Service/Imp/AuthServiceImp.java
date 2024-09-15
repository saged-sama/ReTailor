package com.javafest.Retailor.Service.Imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.javafest.Retailor.Config.JwtService;
import com.javafest.Retailor.Dto.AuthenticationResponse;
import com.javafest.Retailor.Dto.LoginReq;
import com.javafest.Retailor.Entity.Users;
import com.javafest.Retailor.Repository.UsersRepo;
import com.javafest.Retailor.Service.AuthService;

@Service
public class AuthServiceImp implements AuthService {
    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Override
    public Users save(Users user) {

        return usersRepo.save(user);
    }

    public AuthenticationResponse findByUsername(LoginReq loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        var user = usersRepo.findByEmail(loginRequest.getEmail())
                .orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().accessToken(jwtToken).build();
    }
}

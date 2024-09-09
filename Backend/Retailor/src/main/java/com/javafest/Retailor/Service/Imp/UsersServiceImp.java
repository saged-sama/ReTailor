package com.javafest.Retailor.Service.Imp;

import com.javafest.Retailor.Entity.Users;
import com.javafest.Retailor.Repository.UsersRepo;
import com.javafest.Retailor.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImp implements UsersService {
    @Autowired
    private UsersRepo usersRepo;
    @Override
    public Users save(Users user) {
        return usersRepo.save(user);
    }
}

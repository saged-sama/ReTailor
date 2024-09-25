package com.javafest.Retailor.Service.Imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javafest.Retailor.Entity.Forder;
import com.javafest.Retailor.Entity.Users;
import com.javafest.Retailor.Repository.ForderRepo;
import com.javafest.Retailor.Service.ForderService;
import com.javafest.Retailor.Service.UsersService;

@Service
public class ForderServiceImp implements ForderService {
    @Autowired
    private ForderRepo forderRepo;
    @Autowired
    private UsersService usersService;

    @Override
    public Forder createForder(Forder forder) {
        return forderRepo.save(forder);
    }
    
    @Override
    public List<Forder> getForderByUserId(String userId){
        Users user = usersService.getById(userId);
        return forderRepo.findByUserId(user);
    }

    @Override
    public Optional<Forder> getForderById(String id) {
        return forderRepo.findById(id);
    }
}

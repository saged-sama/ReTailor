package com.javafest.Retailor.Service.Imp;

import com.javafest.Retailor.Entity.Tailor;
import com.javafest.Retailor.Repository.TailorRepo;
import com.javafest.Retailor.Service.TailorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TailorServiceImp implements TailorService {

    @Autowired
    private TailorRepo tailorRepo;
    @Override
    public Tailor save(Tailor tailor) {
        return tailorRepo.save(tailor);
    }
}

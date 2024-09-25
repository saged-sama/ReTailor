package com.javafest.Retailor.Service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javafest.Retailor.Entity.Bargain;
import com.javafest.Retailor.Entity.Forder;
import com.javafest.Retailor.Entity.Tailor;
import com.javafest.Retailor.Repository.BargainRepo;
import com.javafest.Retailor.Service.BargainService;

@Service
public class BargainServiceImp implements BargainService {
    @Autowired
    private BargainRepo bargainRepo;

    @Override
    public List<Bargain> getBargainByForder(Forder forder) {
        return bargainRepo.findByForder(forder);
    }

    @Override 
    public List<Bargain> getBargainByTailor(Tailor tailor) {
        return bargainRepo.findByTailor(tailor);
    }

    @Override
    public Bargain createBargain(Bargain bargain) {
        return bargainRepo.save(bargain);
    }
}

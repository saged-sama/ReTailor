package com.javafest.Retailor.Service;

import java.util.List;

import com.javafest.Retailor.Entity.Bargain;
import com.javafest.Retailor.Entity.Forder;
import com.javafest.Retailor.Entity.Tailor;

public interface BargainService {
    public List<Bargain> getBargainByForder(Forder forder);
    public List<Bargain> getBargainByTailor(Tailor tailor);
    public Bargain createBargain(Bargain bargain);
}

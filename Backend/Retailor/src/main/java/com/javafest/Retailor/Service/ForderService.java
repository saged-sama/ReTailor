package com.javafest.Retailor.Service;

import java.util.List;
import java.util.Optional;

import com.javafest.Retailor.Entity.Forder;

public interface ForderService {
    public Forder createForder(Forder forder);
    public List<Forder> getForderByUserId(String userId);
    public Optional<Forder> getForderById(String id);
}

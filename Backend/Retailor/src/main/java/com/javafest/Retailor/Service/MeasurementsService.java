package com.javafest.Retailor.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.javafest.Retailor.Entity.Measurements;

public interface MeasurementsService {

    public Measurements getById(String id);
    public Measurements save(Measurements measurements);
    public Page<Measurements> getAll(int page, int perPage, String sortBy, String sortDir, String filter);

    public List<Measurements> delete(String id);
}

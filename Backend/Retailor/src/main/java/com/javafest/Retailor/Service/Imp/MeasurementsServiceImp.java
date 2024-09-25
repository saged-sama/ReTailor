package com.javafest.Retailor.Service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.javafest.Retailor.Entity.Measurements;
import com.javafest.Retailor.Repository.MeasurementsRepo;
import com.javafest.Retailor.Service.MeasurementsService;
import com.javafest.Retailor.Specification.MeasurementsSpecification;

@Service
public class MeasurementsServiceImp implements MeasurementsService {
    @Autowired
    private MeasurementsRepo measurementsRepo;

    @Override
    public Measurements getById(String id) {
        return measurementsRepo.findById(id).orElse(null);
    }

    @Override
    public Measurements save(Measurements measurements) {
        return measurementsRepo.save(measurements);
    }

    @Override
    public Page<Measurements> getAll(int page, int perPage, String sortBy, String sortDir, String filter) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page-1, perPage, sort);

        Specification<Measurements> spec = Specification.where(null);

        String[] filters = filter.split("&&");
        for(String f : filters) {
            spec = spec.and(parseFilter(f));
            System.out.println(f);
        }

        return measurementsRepo.findAll(spec, pageable);
    }

    private Specification<Measurements> parseFilter(String filter) {
        String[] parts = filter.split("=", 2);
        if(parts.length < 2) {
            return null;
        }

        String key = parts[0].trim();
        String value = parts[1].trim().replace("\"", "").replace("'", "");

        return switch (key) {
            case "customer_id" -> MeasurementsSpecification.hasCusotmerId(value);
            default -> null;
        };
    }

    @Override
    public List<Measurements> delete(String id) {
        measurementsRepo.deleteById(id);
        return measurementsRepo.findAll();
    }
}

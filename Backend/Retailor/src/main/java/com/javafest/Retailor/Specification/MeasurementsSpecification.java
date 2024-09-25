package com.javafest.Retailor.Specification;

import org.springframework.data.jpa.domain.Specification;

import com.javafest.Retailor.Entity.Measurements;

public class MeasurementsSpecification {
    public static Specification<Measurements> hasCusotmerId(String customerId) {
        return (root, query, cb) -> cb.equal(cb.lower(root.get("customerId")), cb.lower(cb.literal(customerId)));
    }
}

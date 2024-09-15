package com.javafest.Retailor.Specification;
import org.springframework.data.jpa.domain.Specification;

import com.javafest.Retailor.Entity.Users;

public class UserSpecification {
    public static Specification<Users> hasEmail(String email) {
        return (root, query, cb) -> cb.equal(cb.lower(root.get("email")), cb.lower(cb.literal(email)));
    }

    public static Specification<Users> hasId(String id) {
        return (root, query, cb) -> cb.equal(cb.lower(root.get("id")), cb.lower(cb.literal(id)));
    }
}
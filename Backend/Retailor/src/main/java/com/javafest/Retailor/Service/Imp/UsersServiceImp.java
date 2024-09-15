package com.javafest.Retailor.Service.Imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.javafest.Retailor.Entity.Users;
import com.javafest.Retailor.Repository.UsersRepo;
import com.javafest.Retailor.Service.UsersService;
import com.javafest.Retailor.Specification.UserSpecification;

@Service
public class UsersServiceImp implements UsersService {
    @Autowired
    private UsersRepo usersRepo;
    @Override
    public Users save(Users user) {
        return usersRepo.save(user);
    }

    @Override
    public Users getByEmail(String email) {
        return usersRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public Users getById(String id) {
        return usersRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public Page<Users> getAll(int page, int perPage, String sortBy, String sortDir, String filter) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page-1, perPage, sort);

        Specification<Users> spec = Specification.where(null);

        String[] filters = filter.split("&&");
        for(String f : filters) {
            spec = spec.and(parseFilter(f));
            System.out.println(f);
        }
        return usersRepo.findAll(spec, pageable);
    }

    private Specification<Users> parseFilter(String filter) {
        String[] parts = filter.split("=", 2);
        if(parts.length < 2) {
            return null;
        }

        String key = parts[0].trim();
        String value = parts[1].trim().replace("\"", "").replace("'", "");

        return switch (key) {
            case "email" -> UserSpecification.hasEmail(value);
            case "id" -> UserSpecification.hasId(value);
            default -> null;
        };
    }
}
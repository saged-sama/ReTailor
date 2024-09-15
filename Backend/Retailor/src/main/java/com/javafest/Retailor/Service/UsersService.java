package com.javafest.Retailor.Service;

import org.springframework.data.domain.Page;

import com.javafest.Retailor.Entity.Users;

public interface UsersService {
    public Users save(Users user);
    public Users getByEmail(String email);
    public Users getById(String id);
    public Page<Users> getAll(int page, int perPage, String sortBy, String sortDir, String filter);
}

package com.javafest.Retailor.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javafest.Retailor.Entity.Users;
import com.javafest.Retailor.Service.UsersService;

@RestController
@RequestMapping("/api/collections/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/records")
    public ResponseEntity<Page<Users>> getAllUsers(
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "10") int perPage,
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "asc") String sortDir,
        @RequestParam(defaultValue = "") String filter
    ) {
        Page<Users> users = usersService.getAll(page, perPage, sortBy, sortDir, filter);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/records/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable String id) {
        System.out.println("id: " + id);
        return ResponseEntity.ok(usersService.getById(id));
    }
}

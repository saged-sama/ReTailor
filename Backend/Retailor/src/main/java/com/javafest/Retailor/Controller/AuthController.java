package com.javafest.Retailor.Controller;

import com.javafest.Retailor.Config.JwtService;
import com.javafest.Retailor.Dto.AuthenticationResponse;
import com.javafest.Retailor.Dto.CustomerDto;
import com.javafest.Retailor.Dto.LoginReq;
import com.javafest.Retailor.Dto.RegisterReq;
import com.javafest.Retailor.Entity.Customer;
import com.javafest.Retailor.Entity.Users;
import com.javafest.Retailor.Enum.Role;
import com.javafest.Retailor.Repository.UsersRepo;
import com.javafest.Retailor.Service.AuthService;
import com.javafest.Retailor.Service.CustomerService;
import com.javafest.Retailor.Service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/collections")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private FileService fileService;

    @Transactional
    @PostMapping("/auth/register")
    public ResponseEntity<?> saveCustomer(@ModelAttribute RegisterReq registerReq,
                                                               @RequestParam("avatar") MultipartFile[] files) throws IOException {
        Users users = new Users();
        users.setEmail(registerReq.getEmail());
        users.setPassword(passwordEncoder.encode(registerReq.getPassword()));
        Set<Role> roles= new HashSet<>();
        roles.add(Role.ROLE_CUSTOMER);
        users.setRoles(roles);
        Users users1 = authService.save(users);
        if(users1 == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        String jwtToken = jwtService.generateToken(users1);


        Customer customer= new Customer();
        customer.setName(registerReq.getName());
        customer.setGender(registerReq.getGender());
        customer.setAddress(registerReq.getAddress());
        customer.setPhone(registerReq.getPhone());
        customer.setUsers(users1);
        try {
            // Save the files and get the paths
            List<String> filePaths = fileService.saveFiles(files);
            for(String file : filePaths){
                customer.setAvatar(file);
            }
        } catch (IOException e) {
            return new ResponseEntity<>("Could not upload the files.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        CustomerDto customerDto = customerService.save(customer);

        System.out.println(customerDto);


        return ResponseEntity.ok(AuthenticationResponse.builder().accessToken(jwtToken).build());

    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> loginUser(@ModelAttribute LoginReq loginReq){
        return ResponseEntity.ok(authService.findByUsername(loginReq));
    }
}

package com.javafest.Retailor.Controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javafest.Retailor.Config.JwtService;
import com.javafest.Retailor.Dto.AuthenticationResponse;
import com.javafest.Retailor.Dto.CustomerDto;
import com.javafest.Retailor.Dto.LoginReq;
import com.javafest.Retailor.Dto.RegisterReq;
import com.javafest.Retailor.Entity.Customer;
import com.javafest.Retailor.Entity.Users;
import com.javafest.Retailor.Enum.Role;
import com.javafest.Retailor.Service.AuthService;
import com.javafest.Retailor.Service.CustomerService;
import com.javafest.Retailor.Service.FileService;

@RestController
@RequestMapping("/api/collections/users")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private FileService fileService;

    @Transactional
    @PostMapping("/records")
    public ResponseEntity<?> saveCustomer(@ModelAttribute RegisterReq registerReq,
                                                               @RequestParam("avatar") MultipartFile[] files) throws IOException {
        
        if(!registerReq.getPassword().equals(registerReq.getPasswordConfirm())){
            return new ResponseEntity<>("Passwords don't match", HttpStatus.BAD_REQUEST);
        }
        Users users = new Users();
        users.setEmail(registerReq.getEmail().trim());
        users.setPassword(passwordEncoder.encode(registerReq.getPassword()));
        Set<Role> roles= new HashSet<>();
        roles.add(Role.ROLE_CUSTOMER);
        users.setRoles(roles);
        Users createdUser = authService.save(users);
        if(createdUser == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        String jwtToken = jwtService.generateToken(createdUser);


        Customer customer= new Customer();
        customer.setName(registerReq.getFirstName() + " " + registerReq.getLastName());
         customer.setGender(registerReq.getGender());
         customer.setAddress(registerReq.getAddress());
        customer.setPhone(registerReq.getPhone().trim());
        customer.setUsers(createdUser);
        try {
            // Save the files and get the paths
            List<String> filePaths = fileService.saveFiles(files);
            for(String file : filePaths){
                customer.setAvatar(file);
            }
        } catch (IOException e) {
            return new ResponseEntity<>("Could not upload the files.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        CustomerDto customerDto = customerService.saveCustomer(customer);

        System.out.println(customerDto);


        return ResponseEntity.ok(AuthenticationResponse.builder().accessToken(jwtToken).build());

    }
    
    @PostMapping("/auth-with-password")
    public ResponseEntity<?> loginUser(@RequestBody LoginReq loginReq){
        System.out.println(loginReq);
        return ResponseEntity.ok(authService.findByUsername(loginReq));
    }
}

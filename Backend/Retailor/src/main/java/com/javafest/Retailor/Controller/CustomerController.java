package com.javafest.Retailor.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javafest.Retailor.Config.JwtService;
import com.javafest.Retailor.Entity.Customer;
import com.javafest.Retailor.Entity.Users;
import com.javafest.Retailor.Service.CustomerService;
import com.javafest.Retailor.Service.FileService;
import com.javafest.Retailor.Service.UsersService;

@RestController
@RequestMapping("/api/collections/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private FileService fileService;

    @GetMapping("/records")
    public ResponseEntity<Customer> getCustomerByUser(@RequestHeader("Authorization") String authHeader) throws Exception{
        String email;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7); // Remove "Bearer " prefix
            email= jwtService.extractUsername(jwt);
        } else {
            throw new Exception("Unauthorized action");
        }

        Users users= usersService.getByEmail(email);
        return ResponseEntity.ok(customerService.getByUsers(users));
    }

    @PatchMapping("/records/{customerId}")
    public ResponseEntity<Customer> updateCustomerDetails(@RequestHeader("Authorization") String authHeader, @PathVariable String customerId, @ModelAttribute Customer customer, @RequestParam(value = "avatarUpload", required=false) MultipartFile[] files) throws Exception{
        String email;
        System.out.println(authHeader);
        if(authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);
            email = jwtService.extractUsername(jwt);
        }
        else{
            throw new Exception("Unauthorized action: No AuthHeader");
        }

        Users users = usersService.getByEmail(email);
        Customer cus = customerService.getByUsers(users);
        if(!(customerId.equals(cus.getId()))){
            throw new Exception("Unauthorized action: User not allowed the action");
        }
        
        if(files != null && files.length > 0){
            fileService.deleteFile(cus.getAvatar());
            List<String> filePaths = fileService.saveFiles(files);
            for(String file : filePaths){
                customer.setAvatar(file);
            }
        }

        return ResponseEntity.ok(customerService.updateCustomer(customer, customerId));
    }
}

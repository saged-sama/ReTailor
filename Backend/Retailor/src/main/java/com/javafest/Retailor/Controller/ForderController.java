package com.javafest.Retailor.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javafest.Retailor.Entity.Customer;
import com.javafest.Retailor.Entity.Forder;
import com.javafest.Retailor.Entity.Forder.OrderStatus;
import com.javafest.Retailor.Entity.Users;
import com.javafest.Retailor.Service.CustomerService;
import com.javafest.Retailor.Service.FileService;
import com.javafest.Retailor.Service.ForderService;
import com.javafest.Retailor.Service.UsersService;

import io.jsonwebtoken.io.IOException;

@RestController
@RequestMapping("/api/collections/forder")
public class ForderController {
    @Autowired
    private ForderService forderService;
    @Autowired
    private FileService fileService;
    @Autowired
    private UsersService usersService;
    @Autowired CustomerService customerService;
    
    @PostMapping("/records")
    public Forder saveForder(@ModelAttribute Forder forder, @RequestParam("usersId") String usersId, @RequestParam("orderImages") MultipartFile[] orderImages) throws java.io.IOException {
        try{
            List<String> images = fileService.saveFiles(orderImages);
            forder.setImages(images);
        }
        catch(IOException e){
            return null;
        }

        Users user = usersService.getById(usersId);
        Customer customer = customerService.getByUsers(user);
        forder.setUserId(user);
        forder.setCustomer(customer);
        forder.setStatus(OrderStatus.PENDING);
        forder.setCustomerBargain(forder.getInitialBargain());
        forder.setTailorBargain("0");
        forder.setFinalBargain(forder.getInitialBargain());
        return forderService.createForder(forder);
    }
    
    @GetMapping("/records")
    public List<Forder> getAllForders(@RequestParam String userId) {
        return forderService.getForderByUserId(userId);
    }
    
    @GetMapping("/records/{id}")
    public Optional<Forder> getForder(@PathVariable String id) {
        System.out.println(id);
        return forderService.getForderById(id);
    }
    
}

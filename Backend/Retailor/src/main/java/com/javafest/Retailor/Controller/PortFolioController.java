package com.javafest.Retailor.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javafest.Retailor.Config.JwtService;
import com.javafest.Retailor.Entity.Portfolio;
import com.javafest.Retailor.Entity.Tailor;
import com.javafest.Retailor.Entity.Users;
import com.javafest.Retailor.Service.PortfolioService;
import com.javafest.Retailor.Service.TailorService;
import com.javafest.Retailor.Service.UsersService;



@RestController
@RequestMapping("/api/collections/portfolio")
public class PortFolioController {
    @Autowired
    private PortfolioService portfolioService;
    @Autowired
    private TailorService tailorService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/records")
    @Transactional
    public ResponseEntity<Portfolio> savePortfolio(@ModelAttribute Portfolio portfolio,
                                                   @RequestHeader("Authorization") String authHeader)throws Exception{
        String email;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7); // Remove "Bearer " prefix
            email= jwtService.extractUsername(jwt);
        } else {
            throw new Exception("Invalid Authorization header.");
        }
        Users users= usersService.getByEmail(email);
        Tailor tailor= tailorService.getByUser(users);
        portfolio.setTailor(tailor);
        tailor.setPortfolio(portfolio);
        //tailorService.save(tailor);
        return ResponseEntity.ok(portfolioService.savePortfolio(portfolio));
    }

    @GetMapping("/records/{tailorId}")
    public Portfolio getMethodName(@PathVariable String tailorId) {
        System.out.println(tailorId);
        return portfolioService.getPortfolioByTailorsId(tailorId);
    }
}

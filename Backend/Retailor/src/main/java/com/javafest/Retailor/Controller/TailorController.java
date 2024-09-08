package com.javafest.Retailor.Controller;

import com.javafest.Retailor.Entity.Tailor;
import com.javafest.Retailor.Service.TailorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/collections")
public class TailorController {
    @Autowired
    private TailorService tailorService;

    @PostMapping("/tailor/save")
    ResponseEntity<Tailor> saveTailor(@ModelAttribute Tailor tailor){
        try {
            Tailor savedTailor = tailorService.save(tailor);

            return new ResponseEntity<>(savedTailor, HttpStatus.CREATED);
        } catch (Exception e) {
            // Handle any exceptions and return a 500 (INTERNAL SERVER ERROR) status
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

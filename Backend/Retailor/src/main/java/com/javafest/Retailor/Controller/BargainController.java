package com.javafest.Retailor.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javafest.Retailor.Entity.Bargain;
import com.javafest.Retailor.Entity.Forder;
import com.javafest.Retailor.Entity.Tailor;
import com.javafest.Retailor.Repository.BargainRepo;
import com.javafest.Retailor.Service.BargainService;
import com.javafest.Retailor.Service.ForderService;
import com.javafest.Retailor.Service.TailorService;


@RestController
@RequestMapping("/api/collections/bargain")
public class BargainController {
    @Autowired
    private BargainService bargainService;
    @Autowired
    private BargainRepo bargainRepo;
    @Autowired
    private ForderService forderService;
    @Autowired
    private TailorService tailorService;

    @GetMapping("/records/forder/{forderId}")
    public List<Bargain> getMethodName(@PathVariable String forderId) {
        Forder forder = forderService.getForderById(forderId).get();
        return bargainService.getBargainByForder(forder);
    }
    
    @GetMapping("/records/tailor/{tailorId}")
    public List<Bargain> getByTailor(@PathVariable String tailorId) {
        Tailor tailor = tailorService.getById(tailorId);
        return bargainService.getBargainByTailor(tailor);
    }

    @PostMapping("/records")
    public Bargain postMethodName(@RequestBody Bargain bargain, @RequestParam String tailorId, @RequestParam String forderId) {
        Tailor tailor = tailorService.getById(tailorId);
        bargain.setTailor(tailor);
        Forder forder = forderService.getForderById(forderId).get();
        bargain.setForder(forder);
        return bargainService.createBargain(bargain);
    }
    
    @PatchMapping("/records/{bargainId}")
    public Bargain updateBargain(@PathVariable String bargainId, @RequestBody Bargain bargain) {
        Bargain bargainObj = bargainRepo.findById(bargainId).get();
        bargainObj.setCustomerProposal(bargain.getCustomerProposal());
        bargainObj.setTailorProposal(bargain.getTailorProposal());

        return bargainRepo.save(bargainObj);
    }
}

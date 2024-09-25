package com.javafest.Retailor.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javafest.Retailor.Entity.Portfolio;
import com.javafest.Retailor.Entity.PortfolioImages;
import com.javafest.Retailor.Repository.PortfolioImagesRepo;
import com.javafest.Retailor.Service.FileService;
import com.javafest.Retailor.Service.PortfolioService;


@RestController
@RequestMapping("/api/collections/portfolioImages")
public class PortFolioImageController {
    @Autowired
    private PortfolioService portfolioService;
    @Autowired
    private FileService fileService;
    @Autowired
    private PortfolioImagesRepo portfolioImagesRepo;

    @PostMapping("/records")
    @Transactional
    public ResponseEntity<PortfolioImages> savePortfolioImages(@RequestParam String portfolioId,
                                                               @RequestParam String description,
                                                               @RequestParam("workImage") MultipartFile[] files) throws IOException {
        Portfolio portfolio= portfolioService.getPortfolioByPortfolioId(portfolioId);
        PortfolioImages portfolioImages=new PortfolioImages();
        portfolioImages.setPortfolio(portfolio);
        portfolioImages.setDescription(description);
        List<String> images=fileService.saveFiles(files);
        portfolioImages.setImage(images.get(0));
        List<PortfolioImages> portfolioImagesList=new ArrayList<>();
        portfolioImagesList.add(portfolioImages);
        portfolio.setImages(portfolioImagesList);
        portfolioImagesRepo.save(portfolioImages);
        return ResponseEntity.ok(portfolioImages);
    }

    @GetMapping("/records/{portfolioId}")
    public List<PortfolioImages> getMethodName(@PathVariable String portfolioId) {
        return portfolioImagesRepo.findByPortfolioId(portfolioId);
    }
    
}

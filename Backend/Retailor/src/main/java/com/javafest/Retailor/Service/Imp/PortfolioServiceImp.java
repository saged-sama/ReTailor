package com.javafest.Retailor.Service.Imp;

import com.javafest.Retailor.Entity.Portfolio;
import com.javafest.Retailor.Entity.PortfolioImages;
import com.javafest.Retailor.Repository.PortfolioImagesRepo;
import com.javafest.Retailor.Repository.PortfolioRepo;
import com.javafest.Retailor.Service.FileService;
import com.javafest.Retailor.Service.PortfolioService;
import com.javafest.Retailor.Service.TailorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PortfolioServiceImp implements PortfolioService {
    @Autowired
    private PortfolioRepo portfolioRepo;

    @Autowired
    private FileService fileService;
    @Autowired
    private TailorService tailorService;
    @Autowired
    private PortfolioImagesRepo portfolioImagesRepo;
    @Override
    public Portfolio savePortfolio(Portfolio portfolio) {
        return portfolioRepo.save(portfolio);
    }

    @Override
    @Transactional
    public Portfolio updatePortfolio(Portfolio portfolio) throws IOException {
        Portfolio portfolio1= portfolioRepo.findById(portfolio.getId()).orElseThrow(() -> new RuntimeException("No Portfolio Found!"));
        portfolio1.setTitle(portfolio.getTitle());
        portfolio1.setDescription(portfolio.getDescription());
        portfolio1.getTailor().setPortfolio(portfolio1);
        //tailorService.save(portfolio1.getTailor());
        return portfolioRepo.save(portfolio1);
    }

    @Override
    @Transactional
    public String deletePortfolio(String portfolioId) throws IOException {
        Portfolio portfolio1= portfolioRepo.findById(portfolioId).orElseThrow(() -> new RuntimeException("No Portfolio Found!"));
        portfolio1.getTailor().setPortfolio(null);
        portfolioRepo.deleteById(portfolioId);
        portfolioImagesRepo.deleteByPortfolioId(portfolioId);
        return "Successfully Deleted";
    }

    @Override
    public Portfolio getPortfolioByTailorsId(String tailorId) {
        return portfolioRepo.findByTailorId(tailorId);
    }

    @Override
    public Portfolio getPortfolioByPortfolioId(String portfolioId) {
        return portfolioRepo.findById(portfolioId).orElseThrow();
    }
}

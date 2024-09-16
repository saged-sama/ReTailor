package com.javafest.Retailor.Service;

import com.javafest.Retailor.Entity.Portfolio;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PortfolioService {
    public Portfolio savePortfolio(Portfolio portfolio);
    public Portfolio updatePortfolio(Portfolio portfolio, MultipartFile[] files) throws IOException;
    public String deletePortfolio(Long portfolioId) throws IOException;

    public Portfolio getPortfolioByTailorsId(Long tailorId);
}

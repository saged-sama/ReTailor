package com.javafest.Retailor.Service;

import java.io.IOException;

import com.javafest.Retailor.Entity.Portfolio;

public interface PortfolioService {
    public Portfolio savePortfolio(Portfolio portfolio);
    public Portfolio updatePortfolio(Portfolio portfolio) throws IOException;
    public String deletePortfolio(String portfolioId) throws IOException;

    public Portfolio getPortfolioByTailorsId(String tailorId);
    public Portfolio getPortfolioByPortfolioId(String portfolioId);
}

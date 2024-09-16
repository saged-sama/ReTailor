package com.javafest.Retailor.Repository;

import com.javafest.Retailor.Entity.Portfolio;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioRepo extends JpaRepository<Portfolio ,String > {
    public Portfolio findByTailorId(String tailorId);
    @SuppressWarnings("null")
    public void deleteById(String portfolioId);
    @SuppressWarnings("null")
    public Optional<Portfolio> findById(String id);

}

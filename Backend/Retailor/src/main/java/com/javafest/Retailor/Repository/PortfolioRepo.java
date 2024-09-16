package com.javafest.Retailor.Repository;

import com.javafest.Retailor.Entity.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PortfolioRepo extends JpaRepository<Portfolio ,Long > {
    public Portfolio findByTailorId(Long tailorId);
    public void deleteById(Long portfolioId);
    public Optional<Portfolio> findById(Long id);

}

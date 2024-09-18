package com.javafest.Retailor.Repository;

import com.javafest.Retailor.Entity.PortfolioImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortfolioImagesRepo extends JpaRepository<PortfolioImages,String> {
    public List<PortfolioImages> findByPortfolioId(String id);
    public void deleteByPortfolioId(String id);
}

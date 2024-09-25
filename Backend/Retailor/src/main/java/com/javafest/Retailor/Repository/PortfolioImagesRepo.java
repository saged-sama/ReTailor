package com.javafest.Retailor.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javafest.Retailor.Entity.PortfolioImages;

@Repository
public interface PortfolioImagesRepo extends JpaRepository<PortfolioImages,String> {
    public List<PortfolioImages> findByPortfolioId(String id);
    public void deleteByPortfolioId(String id);
}

package com.javafest.Retailor.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioImages {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String image;
    private String description;
    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;
}

package com.javafest.Retailor.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewAndRating {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String Id;
    @ManyToOne
    @JoinColumn(name= "tailorId")
    private Tailor tailorList;
    @ManyToOne
    @JoinColumn(name= "customerId")
    private Customer customer;
    private Integer rating;
    @Column(length = 1000)
    private String review;
}

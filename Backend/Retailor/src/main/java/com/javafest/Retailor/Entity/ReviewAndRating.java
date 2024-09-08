package com.javafest.Retailor.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.context.LifecycleAutoConfiguration;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewAndRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
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

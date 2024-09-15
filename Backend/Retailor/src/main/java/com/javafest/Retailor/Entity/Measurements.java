package com.javafest.Retailor.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Measurements {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Double height;
    private Double neck;
    private Double shoulder;
    private Double chest;
    private Double upperBust;
    private Double lowerBust;
    private Double waist;
    private Double hip;
    private Double armhole;
    private Double sleeveLength;
    private Double bicep;
    private Double elbow;
    private Double wrist;
    private Double backWidth;
    private Double frontLength;
    private Double backLength;
    private Double rise;
    private Double thigh;
    private Double Knee;
    private Double calf;
    private Double inseam;
    private Double outseam;
    private Double bustToWaist;
    private Double waistToKnee;
    private Double waistToHem;
    @OneToOne
    @JoinColumn(name = "customerId")
    private Customer customerId;

}

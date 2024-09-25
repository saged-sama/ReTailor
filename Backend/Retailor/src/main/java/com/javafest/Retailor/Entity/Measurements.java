package com.javafest.Retailor.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private String gender;
    private String height;
    private String weight;
    private Integer age;
    private String neck;
    private String shoulder;
    private String chest;
    private String upperBust;
    private String lowerBust;
    private String waist;
    private String hip;
    private String armhole;
    private String sleeveLength;
    private String bicep;
    private String elbow;
    private String wrist;
    private String backWidth;
    private String frontLength;
    private String backLength;
    private String verticalTrunk;
    private String rise;
    private String thigh;
    private String Knee;
    private String calf;
    private String inseam;
    private String outseam;
    private String bustToWaist;
    private String waistToKnee;
    private String waistToHem;
    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "customerId")
    private Customer customerId;
}

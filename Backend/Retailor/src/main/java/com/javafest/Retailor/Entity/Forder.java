package com.javafest.Retailor.Entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Forder {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String title;
    private String description;
    private String category;
    @Column(length=1000)
    private List<String> images;

    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;
    private String cvv;

    private String receiverName;
    private String email;
    private String phone;
    private String address;
    private String privacy;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

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

    private String initialBargain;

    private String customerBargain;
    private String tailorBargain;

    private String finalBargain;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Users userId;

    @ManyToOne
    @JoinColumn(name = "customer")
    private Customer customer;

    public enum OrderStatus {
        PENDING,
        ACCEPTED,
        REJECTED
    }
}

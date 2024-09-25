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
public class PaymentInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;
    private String cvv;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "userId")
    private Users userId;
}
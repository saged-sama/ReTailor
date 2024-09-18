package com.javafest.Retailor.Entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.javafest.Retailor.Enum.TailorStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Tailor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String Id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, length = 1000)
    private String bio;
    private String skills;
    @OneToMany(mappedBy = "tailorList",cascade = CascadeType.ALL)
    private List<ReviewAndRating> reviewAndRating;
    @Enumerated(EnumType.STRING)
    private TailorStatus tailorStatus;
    @OneToOne(mappedBy = "tailor", cascade = CascadeType.ALL)
    @JsonIgnore
    private Portfolio portfolio;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="Tailor_Product",
            joinColumns = @JoinColumn(name="Tailor_id"),
            inverseJoinColumns = @JoinColumn(name = "Product_id")
    )
    @JsonIgnore
    private Set<Product> products= new HashSet<>();
    @Embedded
    private NationalId nationalId;
    @CreationTimestamp
    private LocalDateTime submissionDate;
    private LocalDateTime approvalDate;
    private String location;

    @ElementCollection
    @CollectionTable(name = "tailor_shirt_design", joinColumns = @JoinColumn(name = "tailor_id"))
    @Column(name = "shirt_design", length = 100)
    private List<String> shirtDesign;

    @ElementCollection
    @CollectionTable(name = "tailor_pant_design", joinColumns = @JoinColumn(name = "tailor_id"))
    @Column(name = "pant_design", length = 100)
    private List<String> pantDesign;

    @ElementCollection
    @CollectionTable(name = "tailor_suite_design", joinColumns = @JoinColumn(name = "tailor_id"))
    @Column(name = "suite_design", length = 100)
    private List<String> suiteDesign;

    @ElementCollection
    @CollectionTable(name = "tailor_threePiece_design", joinColumns = @JoinColumn(name = "tailor_id"))
    @Column(name = "threePiece_design", length = 100)
    private List<String> threePieceDesign;

    @ElementCollection
    @CollectionTable(name = "tailor_saree_design", joinColumns = @JoinColumn(name = "tailor_id"))
    @Column(name = "saree_design", length = 100)
    private List<String> sareeDesign;

    @JsonIgnore
    @OneToMany(mappedBy = "tailor", cascade = CascadeType.ALL)
    private List<Orders> orders;
    @JsonIgnore
    @OneToMany(mappedBy = "tailor", cascade = CascadeType.ALL)
    private List<TailoringOrder> tailoringOrders;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Users users;

    public void addProduct(Product product) {
        this.products.add(product);
        product.getTailors().add(this); // Sync with the inverse side
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
        product.getTailors().remove(this); // Sync with the inverse side
    }
}

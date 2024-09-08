package com.javafest.Retailor.Entity;

import com.javafest.Retailor.Enum.TailorStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Tailor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
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
    private Portfolio portfolio;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="Tailor_Product",
            joinColumns = @JoinColumn(name="Tailor_id"),
            inverseJoinColumns = @JoinColumn(name = "Product_id")
    )
    private Set<Product> products;
    private String nationalId;
    private String tradeLicense;
    @CreationTimestamp
    private LocalDateTime submissionDate;
    private LocalDateTime approvalDate;
    private String location;

    @ElementCollection
    @CollectionTable(name = "tailor_shirt_design", joinColumns = @JoinColumn(name = "tailor_id"))
    @Column(name = "shirt_design", length = 100)
    private Set<String> shirtDesign;

    @ElementCollection
    @CollectionTable(name = "tailor_pant_design", joinColumns = @JoinColumn(name = "tailor_id"))
    @Column(name = "pant_design", length = 100)
    private Set<String> pantDesign;

    @ElementCollection
    @CollectionTable(name = "tailor_suite_design", joinColumns = @JoinColumn(name = "tailor_id"))
    @Column(name = "suite_design", length = 100)
    private Set<String> suiteDesign;

    @ElementCollection
    @CollectionTable(name = "tailor_threePiece_design", joinColumns = @JoinColumn(name = "tailor_id"))
    @Column(name = "threePiece_design", length = 100)
    private Set<String> threePieceDesign;

    @ElementCollection
    @CollectionTable(name = "tailor_saree_design", joinColumns = @JoinColumn(name = "tailor_id"))
    @Column(name = "saree_design", length = 100)
    private Set<String> sareeDesign;

    @OneToMany(mappedBy = "tailor", cascade = CascadeType.ALL)
    private List<Orders> orders;
    @OneToMany(mappedBy = "tailor", cascade = CascadeType.ALL)
    private List<TailoringOrder> tailoringOrders;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Users users;
}

package com.javafest.Retailor.Entity;

import jakarta.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String Id;
    @Column(nullable = false)
    private String name;
    @Column(length = 1000)
    private String description;
    @Column(nullable = false)
    private String category;

    @ElementCollection
    @CollectionTable(
            name = "product_image",
            joinColumns = @JoinColumn(name = "product_id")
    )
    @Column(length = 100)
    private List<String> images;

    private Boolean isCustomizable;
    private Double basePrice;
    @ManyToMany(mappedBy = "products")
    private Set<Tailor> tailors=new HashSet<>();
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProductSize> sizes;
    private Date soldAt;
    @Version
    private int version;
    private int totalCount;
    private int soldCount;

    public void addTailor(Tailor tailor) {
        this.tailors.add(tailor);
        tailor.getProducts().add(this); // Sync with the owning side
    }

    public void removeTailor(Tailor tailor) {
        this.tailors.remove(tailor);
        tailor.getProducts().remove(this); // Sync with the owning side
    }
}
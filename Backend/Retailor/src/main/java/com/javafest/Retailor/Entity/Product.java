package com.javafest.Retailor.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
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
    @JsonIgnore
    private Set<Tailor> tailors;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private Date soldAt;
    private Boolean availability;
}

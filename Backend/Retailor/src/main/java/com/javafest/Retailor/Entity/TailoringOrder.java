package com.javafest.Retailor.Entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.javafest.Retailor.Enum.OrderStatus;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
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
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TailoringOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    @JoinColumn(name = "customerId", nullable = false)
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "tailorId", nullable = false)
    private Tailor tailor;
    private String type;

    @ElementCollection
    @CollectionTable(name = "tailoring_order_images", joinColumns = @JoinColumn(name = "order_id"))
    @Column(length = 100)
    private List<String> images;

    @ElementCollection
    @CollectionTable(name = "tailoring_order_customization", joinColumns = @JoinColumn(name = "order_id"))
    @Column(length = 100)
    private List<String> customization;

    @Column(length = 1000)
    private String specificRequirements;
    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}

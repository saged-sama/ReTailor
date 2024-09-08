package com.javafest.Retailor.Entity;

import com.javafest.Retailor.Enum.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.query.Order;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TailoringOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    private Set<String> images;

    @ElementCollection
    @CollectionTable(name = "tailoring_order_customization", joinColumns = @JoinColumn(name = "order_id"))
    @Column(length = 100)
    private Set<String> customization;

    @Column(length = 1000)
    private String specificRequirements;
    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}

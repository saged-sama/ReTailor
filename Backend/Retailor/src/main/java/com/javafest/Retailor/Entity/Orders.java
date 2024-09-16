package com.javafest.Retailor.Entity;


import com.javafest.Retailor.Enum.OrderStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "tailorId")
    private Tailor tailor;
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;
    private String size;
    private Integer quantity;
    private Double price;
    private String destinationAddress;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;
}
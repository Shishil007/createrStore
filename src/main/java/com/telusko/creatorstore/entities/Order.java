package com.telusko.creatorstore.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.telusko.creatorstore.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @Builder.Default
    private List<OrderItem> items= new ArrayList<>();
    @NotBlank
    @Column(name = "customer_name", nullable = false)
    private String customerName;
    @NotBlank
    @Email
    @Column(name = "customer_email", nullable = false)
    private String customerEmail;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
    @NotNull
    @DecimalMin(value = "0.0", message = "Total price can not be negative")
    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;
    @Column(nullable = false, updatable = false, name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}

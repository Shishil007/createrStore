package com.telusko.creatorstore.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "item_price", nullable = false)
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false, message = "Price of item must be greater than 0")
    private BigDecimal itemPriceAtPurchase;
    @Column(nullable = false)
    @NotNull
    @Min(value = 1, message = "Quantity should be at least 1")
    private Integer quantity;

    //TODO:relations
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id", referencedColumnName = "id", nullable = false)
    private Product product;

}

package com.codecool.vizsgaremek.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class OrderItem implements Serializable {
    @EmbeddedId
    private OrderItemId orderItemId;

    @MapsId("orderId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;

    @MapsId("productId")
    @OneToOne
    private Product product;

    private int quantity;
}

@Embeddable
@Data
class OrderItemId implements Serializable {
    private Long orderId;
    private Long productId;
}

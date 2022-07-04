package com.codecool.vizsgaremek.entity;


import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@NamedEntityGraph(
        name="product",
        attributeNodes = {
                @NamedAttributeNode("id"),
                @NamedAttributeNode("id"),
                @NamedAttributeNode("id")
        }
)
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(
            foreignKey =
            @ForeignKey(name = "order_id")
    )
    @JsonBackReference
    private Order order;
}

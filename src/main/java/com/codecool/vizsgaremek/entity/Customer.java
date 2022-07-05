package com.codecool.vizsgaremek.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @Column(unique = true)
    private String email;
    private String telephoneNumber;
    private String address;

    @OneToMany(mappedBy = "customer", cascade = { CascadeType.REMOVE, CascadeType.PERSIST })
    @JsonManagedReference
    private List<Order> orders;

}

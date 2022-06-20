package com.codecool.vizsgaremek.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Email
    @Column(unique = true)
    private String email;
    private String telephoneNumber;
    private String address;

    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY)
    private List<Order> orders;

}

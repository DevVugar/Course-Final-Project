package com.example.finalproject.model.entity;

import com.example.finalproject.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;

    private String email;
    private String passwordHash;
    private String fullName;
    private String address;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Timestamp createdAt;
    private Timestamp updatedAt;


    @OneToOne(mappedBy = "user")
    private Basket basket;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private List<Order> orders;


}


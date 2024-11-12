package com.example.finalproject.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;


    @ManyToMany
    @JoinTable(name="wishList_product"
    ,joinColumns = @JoinColumn(name = "wishList_id")
            ,inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;
}

package com.example.finalproject.model.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Timestamp createdAt;
    private Timestamp updatedAt;



//    @ManyToOne
//    @JoinColumn(name = "order_id", referencedColumnName = "id")
//    private Order orderByOrderId;

    @ManyToMany
    @JoinTable(name = "basket_product",joinColumns = @JoinColumn(name = "basket_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;




    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;  // Adding relation to User

}
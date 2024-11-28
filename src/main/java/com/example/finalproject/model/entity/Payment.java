package com.example.finalproject.model.entity;

import com.example.finalproject.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime paymentDate;
    private Double totalAmount;
    private String currency;
    private String shippingAddress;
    private String paymentMethod;


    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "basket_id", referencedColumnName = "id")
    private Basket basket;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cart_id",referencedColumnName = "id")
    private Cart cart;

}

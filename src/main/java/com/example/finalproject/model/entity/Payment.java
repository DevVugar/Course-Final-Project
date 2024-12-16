package com.example.finalproject.model.entity;

import com.example.finalproject.enums.PaymentMethod;
import com.example.finalproject.enums.PaymentStatus;
import com.example.finalproject.model.dto.response.ProductResponseDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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
    private Double shippingAmount;
    private String currency;
    private String paymentMethod;

    @Lob
    @Column(name = "products_response_json", columnDefinition = "TEXT")
    private String productsResponseJson;

    @Transient
    private List<ProductResponseDto> productsResponse;


    public List<ProductResponseDto> getProducts() {
        if (productsResponse == null && productsResponseJson != null) {
            productsResponse = convertFromJson(productsResponseJson);
        }
        return productsResponse;
    }

    public void setProducts(List<ProductResponseDto> productsResponse) {
        this.productsResponse = productsResponse;
        this.productsResponseJson = convertToJson(productsResponse);
    }

    private String convertToJson(List<ProductResponseDto> productsResponse) {
        try {
            return new ObjectMapper().writeValueAsString(productsResponse);
        } catch (Exception e) {
            throw new RuntimeException("Error serializing ProductResponse list to JSON", e);
        }
    }

    private List<ProductResponseDto> convertFromJson(String productsResponseJson) {
        try {
            return new ObjectMapper().readValue(productsResponseJson, new TypeReference<List<ProductResponseDto>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing JSON to ProductResponse list", e);
        }
    }

    @OneToOne
    @JoinColumn(name = "shipping_id", referencedColumnName = "id")
    private Shipping shipping;


    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "basket_id", referencedColumnName = "id")
    private Basket basket;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "card_id",referencedColumnName = "id")
    private Card cart;

}

package com.example.finalproject.repository;

import com.example.finalproject.model.entity.Brand;
import com.example.finalproject.model.entity.Order;
import com.example.finalproject.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByBrand(Brand brand);

    List<Product> findByName(String name);

}

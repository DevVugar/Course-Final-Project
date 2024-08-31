package com.example.finalproject.service;

import com.example.finalproject.model.entity.Product;

import java.util.List;

public interface ProductService {
    Product add(Product product);

    Product getById(Long id);

    Product update(Product product);

    void delete(Long id);

    List<Product> getAll();
}

package com.example.finalproject.service.impl;

import com.example.finalproject.exception.NotFoundException;
import com.example.finalproject.mapping.ProductMapping;
import com.example.finalproject.model.dto.response.ProductResponseDto;
import com.example.finalproject.model.entity.Basket;
import com.example.finalproject.model.entity.Product;
import com.example.finalproject.model.entity.User;
import com.example.finalproject.repository.BasketRepository;
import com.example.finalproject.repository.ProductRepository;
import com.example.finalproject.repository.UserRepository;
import com.example.finalproject.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final UserRepository userRepository;
    private final ProductMapping productMapping;
    private final ProductRepository productRepository;

    @Transactional
    @Override
    public ProductResponseDto add(Long userId, Long productId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));

        Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product not found"));

        Basket basket = user.getBasket();
        if (basket == null) {
            basket = new Basket();
            basket.setProducts(new ArrayList<>());
            basket.setCreatedAt(LocalDateTime.now());
            user.setBasket(basket);
        }

        //basket.setUser(user);
        basket.getProducts().add(product);
        basket.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);

        return productMapping.toResponse(product);
    }


    @Override
    public void delete(Long userId, Long productId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product not found"));

        Basket basket = user.getBasket();

        if (basket == null) {
            throw new NotFoundException("Basket is empty");
        }

        List<Product> products = basket.getProducts();

        products.remove(product);

        basket.setUpdatedAt(LocalDateTime.now());
        if (products.size() == 0) {
            user.setBasket(null);
        }
        userRepository.save(user);
    }

}

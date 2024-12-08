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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final UserRepository userRepository;
    private final ProductMapping productMapping;
    private final ProductRepository productRepository;


    @Transactional
    @Override
    public ProductResponseDto add(Long userId, Long productId) {
        log.info("ActionLog.addToBasket.started: userId {}, productId {}", userId, productId);

        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));

        Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product not found"));

        Basket basket = user.getBasket();
        if (basket == null) {
            log.info("ActionLog.addToBasket.newBasketCreated: userId {}", userId);
            basket = new Basket();
            basket.setProducts(new ArrayList<>());
            basket.setCreatedAt(LocalDateTime.now());
            user.setBasket(basket);
        }
        basket.getProducts().add(product);
        basket.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);

        log.info("ActionLog.addToBasket.success: userId {}, productId {}", userId, productId);

        return productMapping.toResponse(product);
    }


    @Override
    public void delete(Long userId, Long productId) {
        log.info("ActionLog.removeFromBasket.started: userId {}, productId {}", userId, productId);

        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product not found"));

        Basket basket = user.getBasket();

        if (basket == null) {
            log.error("ActionLog.removeFromBasket.basketEmpty: userId {}", userId);
            throw new NotFoundException("Basket is empty");
        }

        List<Product> products = basket.getProducts();

        products.remove(product);

        basket.setUpdatedAt(LocalDateTime.now());
        if (products.size() == 0) {
            log.info("ActionLog.removeFromBasket.basketCleared: userId {}", userId);
            user.setBasket(null);
        }
        userRepository.save(user);
        log.info("ActionLog.removeFromBasket.success: userId {}, productId {}", userId, productId);
    }

}

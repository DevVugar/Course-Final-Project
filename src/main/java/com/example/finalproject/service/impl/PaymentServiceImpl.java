package com.example.finalproject.service.impl;

import com.example.finalproject.enums.PaymentStatus;
import com.example.finalproject.exception.NotFoundException;
import com.example.finalproject.exception.PaymentException;
import com.example.finalproject.mapping.CartMapping;
import com.example.finalproject.mapping.PaymentMapping;
import com.example.finalproject.model.dto.request.CartRequestDto;
import com.example.finalproject.model.dto.request.PaymentRequestDto;
import com.example.finalproject.model.dto.request.PaymentRequestWithCardDto;
import com.example.finalproject.model.dto.response.PaymentResponseDto;
import com.example.finalproject.model.entity.*;
import com.example.finalproject.repository.*;
import com.example.finalproject.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapping paymentMapping;
    private final CartRepository cartRepository;
    private final CartMapping cartMapping;
    private final ProductRepository productRepository;
    private final BasketRepository basketRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public PaymentResponseDto pay(PaymentRequestWithCardDto requestDto) {
        log.info("payment process is started");

        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found"));


        Basket basket = user.getBasket();
        if (basket == null || basket.getProducts().isEmpty()) {
            throw new NotFoundException("User's basket is empty. Cannot create an order.");
        }

        Payment payment = paymentMapping.toEntity(requestDto);

        double sum = 0;
            for (int i = 0; i < basket.getProducts().size(); i++) {
                sum += basket.getProducts().get(i).getPrice();
            }
            payment.setTotalAmount(sum);

            payment.setBasket(user.getBasket());

            payment.setUser(user);

            payment.setPaymentDate(LocalDateTime.now());


            payment.setPaymentStatus(PaymentStatus.pending);


        try {

            payment = paymentRepository.save(payment);

            if (!processPayment(payment)) {
                throw new PaymentException("Payment processing failed");
            }

            payment.setPaymentStatus(PaymentStatus.completed);


            if (user.getPayments() == null) {
                user.setPayments(new ArrayList<>());
            }
            user.getPayments().add(payment);

            payment = paymentRepository.save(payment);


        } catch (Exception ex) {
            payment.setPaymentStatus(PaymentStatus.failed);
            paymentRepository.save(payment);
            throw new PaymentException("Payment failed: " + ex.getMessage());
        }


        return paymentMapping.toResponse(payment);
    }


    @Override
    @Transactional
    public PaymentResponseDto pay(PaymentRequestDto requestDto) {

        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found"));

        Cart cart = cartRepository.findById(requestDto.getCardId())
                .orElseThrow(() -> new NotFoundException("Cart not found"));

        // Ensure Cart is managed by Hibernate
        if (!cartRepository.existsById(cart.getId())) {
            cart = cartRepository.save(cart);
        }

        Basket basket = user.getBasket();
        if (basket == null || basket.getProducts().isEmpty()) {
            throw new NotFoundException("User's basket is empty. Cannot create an order.");
        }

        Payment payment = paymentMapping.toEntity(requestDto);
        payment.setBasket(basket);
        payment.setCart(cart);
        payment.setUser(user);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setPaymentStatus(PaymentStatus.pending);

        double sum = basket.getProducts().stream()
                .mapToDouble(Product::getPrice)
                .sum();
        payment.setTotalAmount(sum);

        try {
            payment = paymentRepository.save(payment);

            if (!processPayment(payment)) {
                throw new PaymentException("Payment processing failed");
            }

            payment.setPaymentStatus(PaymentStatus.completed);
            payment = paymentRepository.save(payment);

        } catch (PaymentException ex) {
            payment.setPaymentStatus(PaymentStatus.failed);
            paymentRepository.save(payment);
            throw new PaymentException("Payment failed: " + ex.getMessage());
        }

        return paymentMapping.toResponse(payment);
    }



    //    @Transactional
    private boolean processPayment(Payment payment) {
        List<Product> products = payment.getBasket().getProducts();

        for (int i = 0; i < products.size(); i++) {
            int quantity = products.get(i).getStockQuantity();
            if (quantity > 0) {
                quantity -= 1;
                products.get(i).setStockQuantity(quantity);
            } else {
                return false;
            }


        }

        User u = payment.getUser();
        var id = u.getBasket().getId();
        u.setBasket(null);
        payment.setBasket(null);
        basketRepository.deleteById(id);
        //payment.setBasket(null);


        return true;
    }


    @Override
    public PaymentResponseDto getById(Long id) {
        return paymentMapping.toResponse(paymentRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Payment not found")));
    }
}

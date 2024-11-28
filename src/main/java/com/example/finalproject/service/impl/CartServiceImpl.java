package com.example.finalproject.service.impl;

import com.example.finalproject.exception.AlreadyExistsException;
import com.example.finalproject.exception.NotFoundException;
import com.example.finalproject.mapping.CartMapping;
import com.example.finalproject.model.dto.request.CartRequestDto;
import com.example.finalproject.model.dto.response.CartResponseDto;
import com.example.finalproject.model.entity.Cart;
import com.example.finalproject.model.entity.User;
import com.example.finalproject.repository.CartRepository;
import com.example.finalproject.repository.UserRepository;
import com.example.finalproject.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartMapping cartMapping;
    private final UserRepository userRepository;


    @Transactional
    @Override
    public CartResponseDto add(Long id, CartRequestDto requestDto) {
        log.info("adding of cart is started", requestDto.getBankAccount());


        User user = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("User not found"));

        if (user.getCarts() == null) {
            user.setCarts(new HashSet<>());
        }

        if (cartRepository.findByBankAccount(requestDto.getBankAccount()) != null) {
            throw new AlreadyExistsException("This cart has already been used");
        }

        Cart cart = cartMapping.toEntity(requestDto);
        cart.setCreatedAt(LocalDateTime.now());

        user.getCarts().add(cart);
        cart.setId(user.getId());

        userRepository.save(user);


        CartResponseDto responseDto = cartMapping.toResponse(cart);

        log.info("adding of cart is finished", requestDto.getBankAccount());

        return responseDto;
    }


    @Override
    public CartResponseDto getById(Long id) {
        return cartMapping.toResponse(cartRepository.findById(id).orElseThrow(() -> new NotFoundException("cart not found with " + id)));

    }


    @Override
    @Transactional
    public void delete(Long id) {

        Cart cart = cartRepository.findById(id).orElseThrow(() -> new NotFoundException("Cart not found"));
        User user = userRepository.findById(cart.getUserId()).orElseThrow(() -> new NotFoundException("user not found"));
      // User user=cart.getUser();
        user.getCarts().remove(cart);
        if (user.getCarts().isEmpty()) {
            user.setCarts(null);
        }
        userRepository.save(user);

    }

}

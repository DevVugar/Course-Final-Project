package com.example.finalproject.service.impl;

import com.example.finalproject.exception.NotFoundException;
import com.example.finalproject.mapping.CartMapping;
import com.example.finalproject.model.dto.request.CartRequestDto;
import com.example.finalproject.model.dto.response.CartResponseDto;
import com.example.finalproject.model.entity.Cart;
import com.example.finalproject.repository.CartRepository;
import com.example.finalproject.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartMapping cartMapping;


    @Override
    public CartResponseDto add(CartRequestDto requestDto) {
        Cart cart = cartMapping.toEntity(requestDto);

        cart.setCreatedAt(LocalDateTime.now());
        Cart ans= cartRepository.save(cart);

        return cartMapping.toResponse(ans);
    }

    @Override
    public CartResponseDto getById(Long id) {
        return cartMapping.toResponse(cartRepository.findById(id).orElseThrow(()->new NotFoundException("cart not found with "+id)));

    }

    @Override
    public CartResponseDto update(CartRequestDto requestDto) {
        Cart cart = cartMapping.toEntity(requestDto);

        cart.setUpdatedAt(LocalDateTime.now());
        Cart ans= cartRepository.save(cart);

        return cartMapping.toResponse(ans);
    }

    @Override
    public void delete(Long id) {
        cartRepository.deleteById(id);
    }

}

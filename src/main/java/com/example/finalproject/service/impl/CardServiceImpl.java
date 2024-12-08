package com.example.finalproject.service.impl;

import com.example.finalproject.exception.AlreadyExistsException;
import com.example.finalproject.exception.NotFoundException;
import com.example.finalproject.mapping.CardMapping;
import com.example.finalproject.model.dto.request.CardRequestDto;
import com.example.finalproject.model.dto.response.CardResponseDto;
import com.example.finalproject.model.entity.Card;
import com.example.finalproject.model.entity.User;
import com.example.finalproject.repository.CardRepository;
import com.example.finalproject.repository.UserRepository;
import com.example.finalproject.service.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cartRepository;
    private final CardMapping cartMapping;
    private final UserRepository userRepository;


    @Transactional
    @Override
    public CardResponseDto add(Long id, CardRequestDto requestDto) {
        log.info("adding of Cart started for User ID: {}, Bank Account: {}", id, requestDto.getBankAccount());


        User user = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("User not found"));

        if (user.getCards() == null) {
            user.setCards(new HashSet<>());
        }

        if (cartRepository.findByBankAccount(requestDto.getBankAccount()) != null) {
            throw new AlreadyExistsException("This cart has already been used");
        }

        Card cart = cartMapping.toEntity(requestDto);
        cart.setCreatedAt(LocalDateTime.now());

        user.getCards().add(cart);
        cart.setId(user.getId());

        userRepository.save(user);


        CardResponseDto responseDto = cartMapping.toResponse(cart);

        log.info("adding of cart is finished", requestDto.getBankAccount());

        return responseDto;
    }


    @Override
    public CardResponseDto getById(Long id) {
        log.info("getCartById.started for Cart ID: {}", id);

        Card cart = cartRepository.findById(id).orElseThrow(() -> {
            log.error("getCartById.error: Cart not found with ID: {}", id);
            return new NotFoundException("Cart not found with " + id);
        });

        log.info("getCartById.success for Cart ID: {}", id);
        return cartMapping.toResponse(cart);
    }


    @Override
    @Transactional
    public void delete(Long id) {
        log.info("deleteCart.started for Cart ID: {}", id);


        Card cart = cartRepository.findById(id).orElseThrow(() -> new NotFoundException("Cart not found"));
        User user = userRepository.findById(cart.getUserId()).orElseThrow(() -> new NotFoundException("user not found"));
      // User user=cart.getUser();
        user.getCards().remove(cart);
        if (user.getCards().isEmpty()) {
            user.setCards(null);
        }
        userRepository.save(user);
        log.info("ActionLog.deleteCart.finished for Cart ID: {}", id);

    }

}

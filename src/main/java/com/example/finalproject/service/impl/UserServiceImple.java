package com.example.finalproject.service.impl;

import com.example.finalproject.exception.NotFoundException;
import com.example.finalproject.mapping.CardMapping;
import com.example.finalproject.mapping.PaymentMapping;
import com.example.finalproject.mapping.ProductMapping;
import com.example.finalproject.mapping.UserMapping;
import com.example.finalproject.model.dto.ProductDto;
import com.example.finalproject.model.dto.UserDto;
import com.example.finalproject.model.dto.request.ResetPasswordDto;
import com.example.finalproject.model.dto.response.*;
import com.example.finalproject.model.entity.*;
import com.example.finalproject.repository.*;
import com.example.finalproject.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImple implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final BasketRepository basketRepository;
    private final WishListRepository wishListRepository;
    private final ModelMapper modelMapper;
    private final UserMapping userMapping;
    private final ProductMapping productMapping;
    private final CardRepository cartRepository;
    private final CardMapping cartMapping;
    private final PaymentRepository paymentRepository;
    private final PaymentMapping paymentMapping;

    @Override
    public UserResponseDto getById(Long id) {
        log.info("Fetching user by ID: {}", id);
        return modelMapper.map(userRepository.findById(id).
                orElseThrow(() -> new NotFoundException("User not found")), UserResponseDto.class);

    }

    @Override
    public UserResponseDto update(Long id, UserDto userDto) {
        log.info("Updating user with ID: {}", id);
        User u = userRepository.findById(id).orElseThrow();

        userMapping.toUpdate(userDto, u);
        u.setUpdatedAt(LocalDateTime.now());
        User ans = userRepository.save(u);

        log.info("User with ID {} updated successfully", id);
        return userMapping.toResponse(ans);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting user with ID: {}", id);
        userRepository.deleteById(id);
        log.info("User with ID {} deleted successfully", id);
    }

    @Override
    public List<UserResponseDto> getAll() {
        log.info("Fetching all users");
        return userRepository.findAll().stream().map((user) -> userMapping.toResponse(user)).collect(Collectors.toList());
    }

    @Override
    public List<PaymentResponseDto> getPayments(Long id) {
        log.info("Fetching payments for user ID: {}", id);
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));


        return paymentRepository.findByUser(user).stream().map(payment -> {
                    PaymentResponseDto responseDto = paymentMapping.toResponse(payment);
                    responseDto.setProducts(payment.getProducts());

//                responseDto.setProducts(payment.getProducts().stream().map(product ->
//                        productMapping.toResponse(product)).collect(Collectors.toList()));
                    return responseDto;
                }
        ).collect(Collectors.toList());
    }


    @Override
    public List<ProductResponseDto> getProductByBasket(Long id) {
        log.info("Fetching products in basket for user ID: {}", id);
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("user not found"));
        Basket basket = user.getBasket();

        if (basket == null) {
            log.warn("Basket is empty for user ID: {}", id);
            throw new NotFoundException("Basket is empty");
        }

        List<Product> products = basket.getProducts();
        return products.stream().map(product -> productMapping.toResponse(product)).collect(Collectors.toList());
    }

    @Override
    public Set<CardResponseDto> getCart(Long id) {
        log.info("Fetching carts for user ID: {}", id);
        User u = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("User not found"));

        Set<CardResponseDto> carts = u.getCards().stream().map(cart -> cartMapping.toResponse(cart)).collect(Collectors.toSet());


        return carts;
    }

    @Override
    public List<ProductResponseDto> getWisListProduct(Long id) {
        log.info("Fetching wishlist products for user ID: {}", id);
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User Not found"));
        if (user.getWishList()==null) {
            throw new NotFoundException("WishList is empty");
        }
        WishList wishList = user.getWishList();
        List<ProductResponseDto> products = wishList.getProducts().stream().map(product ->
                productMapping.toResponse(product)).collect(Collectors.toList());

        return products;
    }

    @Override
    public String changePassword(ResetPasswordDto resetPasswordDto) {
        log.info("Changing password for user ID: {}", resetPasswordDto.getUserId());

        User user = userRepository.findById(resetPasswordDto.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found"));

        String message = "Successful";

        boolean isOldPasswordCorrect = passwordEncoder.matches(resetPasswordDto.getOldPassword(), user.getPassword());
        boolean isNewPasswordMatch = Objects.equals(resetPasswordDto.getNewPassword(), resetPasswordDto.getRetryNewPassword());

        if (isOldPasswordCorrect && isNewPasswordMatch) {
            user.setPassword(passwordEncoder.encode(resetPasswordDto.getNewPassword()));
            userRepository.save(user);
            log.info("Password changed successfully for user ID: {}", resetPasswordDto.getUserId());
        } else {
            message = "Failed: ";
            if (!isOldPasswordCorrect) {
                message += "Old password is incorrect. ";
            }
            if (!isNewPasswordMatch) {
                message += "New passwords do not match.";
            }
        }

        return message.trim();
    }


}

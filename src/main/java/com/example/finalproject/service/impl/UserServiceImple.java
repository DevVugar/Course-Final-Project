package com.example.finalproject.service.impl;

import com.example.finalproject.exception.NotFoundException;
import com.example.finalproject.mapping.CartMapping;
import com.example.finalproject.mapping.OrderMapping;
import com.example.finalproject.mapping.ProductMapping;
import com.example.finalproject.mapping.UserMapping;
import com.example.finalproject.model.dto.UserDto;
import com.example.finalproject.model.dto.response.*;
import com.example.finalproject.model.entity.*;
import com.example.finalproject.repository.*;
import com.example.finalproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImple implements UserService {


    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final BasketRepository basketRepository;
    private final WishListRepository wishListRepository;
    private final ModelMapper modelMapper;
    private final UserMapping userMapping;
    private final OrderMapping orderMapping;
    private final ProductMapping productMapping;
    private final CartRepository cartRepository;
    private final CartMapping cartMapping;


    @Override
    public UserResponseDto getById(Long id) {
        return modelMapper.map(userRepository.findById(id).
                orElseThrow(() -> new NotFoundException("User not found")), UserResponseDto.class);

    }

    @Override
    public UserResponseDto update(Long id, UserDto userDto) {
        User u = userRepository.findById(id).orElseThrow();

        userMapping.toUpdate(userDto, u);
        u.setUpdatedAt(LocalDateTime.now());
        User ans = userRepository.save(u);


        return userMapping.toResponse(ans);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserResponseDto> getAll() {
        return userRepository.findAll().stream().map((user) -> userMapping.toResponse(user)).collect(Collectors.toList());
    }

    @Override
    public List<OrderResponseDto> getOrders(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        List<OrderResponseDto> orders = orderRepository.findByUser(user).stream().map(order ->
                orderMapping.toResponse(order)).collect(Collectors.toList());


        return null;
    }

    @Override
    public List<ProductResponseDto> getProductByBasket(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("user not found"));
        Basket basket = basketRepository.findByUser(user);
        List<Product> products = basket.getProducts();
        return products.stream().map(product -> productMapping.toResponse(product)).collect(Collectors.toList());
    }

    @Override
    public Set<CartResponseDto> getCarts(Long id) {
        User u = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("User not found"));

        Set<CartResponseDto> carts = u.getCarts().stream().map(cart ->
                cartMapping.toResponse(cart)).collect(Collectors.toSet());


        return carts;
    }

    @Override
    public List<ProductResponseDto> getWisListProduct(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User Not found"));
        WishList wishList = user.getWishList();
        List<ProductResponseDto> products = wishList.getProducts().stream().map(product ->
                productMapping.toResponse(product)).collect(Collectors.toList());

        return products;
    }


}

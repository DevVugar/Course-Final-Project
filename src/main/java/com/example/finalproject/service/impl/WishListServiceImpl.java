package com.example.finalproject.service.impl;

import com.example.finalproject.exception.NotFoundException;
import com.example.finalproject.mapping.ProductMapping;
import com.example.finalproject.mapping.ReviewMapping;
import com.example.finalproject.mapping.UserMapping;
import com.example.finalproject.mapping.WishListMapping;
import com.example.finalproject.model.dto.request.WishListRequestDto;
import com.example.finalproject.model.dto.response.ProductResponseDto;
import com.example.finalproject.model.dto.response.WishListResponseDto;
import com.example.finalproject.model.entity.Product;
import com.example.finalproject.model.entity.User;
import com.example.finalproject.model.entity.WishList;
import com.example.finalproject.repository.ProductRepository;
import com.example.finalproject.repository.ReviewRepository;
import com.example.finalproject.repository.UserRepository;
import com.example.finalproject.repository.WishListRepository;
import com.example.finalproject.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class WishListServiceImpl implements WishListService {

    private final WishListMapping wishListMapping;
    private final WishListRepository wishListRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final UserMapping userMapping;
    private final ProductMapping productMapping;

    @Override
    public void add(WishListRequestDto requestDto) {
        Product product = productRepository.findById(requestDto.getProductId()).orElseThrow(() -> new NotFoundException("Not Found as like Product"));
        User user = userRepository.findById(requestDto.getUserId()).orElseThrow(() -> new NotFoundException("User not found"));
        // user.getWishList().getProducts().add(product);

        if ( user.getWishList()==null) {
         user.setWishList(new WishList());
        }


        WishList wishList = user.getWishList();

        if (wishList.getProducts()==null || wishList.getProducts().size() == 0) {
            wishList.setProducts(new ArrayList<Product>());
        }

        List<Product> products = wishList.getProducts();
        products.add(product);

        wishList.setUser(user);
        wishListRepository.save(wishList);
    }

    @Override
    public void delete(WishListRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId()).orElseThrow(() -> new NotFoundException("User not found"));
        Product product = productRepository.findById(requestDto.getProductId()).orElseThrow(() -> new NotFoundException("Not Found as like Product"));
        WishList wishList = user.getWishList();
        wishList.getProducts().remove(product);
        wishListRepository.save(wishList);

    }

    @Override
    public List<ProductResponseDto> getAll(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));

        if (user.getWishList() == null || user.getWishList().getProducts().size() == 0) {
            throw new NotFoundException("You dont have product in wishList");
        }

        WishList wishList = wishListRepository.findByUser(user);

        return productMapping.toResponse(wishList.getProducts());
    }

}

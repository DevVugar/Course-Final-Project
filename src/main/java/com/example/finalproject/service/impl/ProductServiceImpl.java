package com.example.finalproject.service.impl;

import com.example.finalproject.exception.NotFoundException;
import com.example.finalproject.mapping.ProductMapping;
import com.example.finalproject.mapping.ReviewMapping;
import com.example.finalproject.model.dto.request.ProductRequestDto;
import com.example.finalproject.model.dto.response.ProductResponseDto;
import com.example.finalproject.model.dto.response.ReviewResponseDto;
import com.example.finalproject.model.entity.Product;
import com.example.finalproject.repository.ProductRepository;
import com.example.finalproject.repository.ReviewRepository;
import com.example.finalproject.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final ProductMapping productMapping;
    private final ModelMapper modelMapper;
    private final ReviewMapping reviewMapping;

    @Override
    public ProductResponseDto add(ProductRequestDto requestDto) {
        Product product = productMapping.toEntity(requestDto);

        product.setCreatedAt(LocalDateTime.now());
        productRepository.save(product);


        return productMapping.toResponse(product);
    }

    @Override
    public ProductResponseDto getById(Long id) {
        return productMapping.toResponse(productRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Product not found")));
    }

    @Override
    public ProductResponseDto update(Long id, ProductRequestDto productRequestDto) {
        Product product = productRepository.findById(id).orElseThrow();


        productMapping.toUpdate(productRequestDto, product);

        product.setUpdatedAt(LocalDateTime.now());
        Product ans = productRepository.save(product);


        return productMapping.toResponse(ans);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductResponseDto> getAll() {
        return productRepository.findAll().stream().map(product ->
                productMapping.toResponse(product)).collect(Collectors.toList());
    }


    @Override
    public List<ReviewResponseDto> getReviewByProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
        List<ReviewResponseDto> reviewResponseDtos = reviewRepository.findByProduct(product).stream().map(review ->
                reviewMapping.toResponse(review)).collect(Collectors.toList());

        return reviewResponseDtos;
    }

    @Override
    public List<ProductResponseDto> getProductByName(String name) {
        return productRepository.findByName(name).stream().map(product ->
                productMapping.toResponse(product)).collect(Collectors.toList());
    }
}

package com.example.finalproject.service.impl;

import com.example.finalproject.exception.NotFoundException;
import com.example.finalproject.mapping.ProductMapping;
import com.example.finalproject.mapping.ReviewMapping;
import com.example.finalproject.model.dto.request.ProductRequestDto;
import com.example.finalproject.model.dto.response.ProductResponseAdminDto;
import com.example.finalproject.model.dto.response.ProductResponseDto;
import com.example.finalproject.model.dto.response.ReviewResponseDto;
import com.example.finalproject.model.entity.Product;
import com.example.finalproject.repository.ProductRepository;
import com.example.finalproject.repository.ReviewRepository;
import com.example.finalproject.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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
        log.info("Adding a new product with name: {}", requestDto.getName());

        Product product = productMapping.toEntity(requestDto);

        product.setCreatedAt(LocalDateTime.now());
        productRepository.save(product);

        log.info("Product added successfully with ID: {}", product.getId());
        return productMapping.toResponse(product);
    }

    @Override
    public ProductResponseDto getById(Long id) {
        log.info("Fetching product by ID: {}", id);
        ProductResponseDto response = productMapping.toResponse(productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found")));
        log.info("Product fetched successfully with ID: {}", id);
        return response;
    }

    @Override
    public ProductResponseDto update(Long id, ProductRequestDto productRequestDto) {
        log.info("Updating product with ID: {}", id);
        Product product = productRepository.findById(id).orElseThrow();


        productMapping.toUpdate(productRequestDto, product);

        product.setUpdatedAt(LocalDateTime.now());
        Product ans = productRepository.save(product);

        log.info("Product updated successfully with ID: {}", id);
        return productMapping.toResponse(ans);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting product with ID: {}", id);
        productRepository.deleteById(id);
        log.info("Product deleted successfully with ID: {}", id);
    }

    @Override
    public List<ProductResponseDto> getAll() {
        log.info("Fetching all products");
        List<ProductResponseDto> products = productRepository.findAll().stream()
                .map(product -> productMapping.toResponse(product))
                .collect(Collectors.toList());
        log.info("Fetched {} products", products.size());
        return products;  }


    @Override
    public List<ReviewResponseDto> getReviewByProduct(Long id) {
        log.info("Fetching reviews for product ID: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found"));
        List<ReviewResponseDto> reviews = reviewRepository.findByProduct(product).stream()
                .map(review -> reviewMapping.toResponse(review))
                .collect(Collectors.toList());
        log.info("Fetched {} reviews for product ID: {}", reviews.size(), id);
        return reviews;
    }

    @Override
    public List<ProductResponseDto> getProductByName(String name) {
        log.info("Fetching products by name: {}", name);
        List<ProductResponseDto> products = productRepository.findByName(name).stream()
                .map(product -> productMapping.toResponse(product))
                .collect(Collectors.toList());
        log.info("Fetched {} products with name: {}", products.size(), name);
        return products;
    }

    @Override
    public List<ProductResponseAdminDto> getLowStockProducts() {
        log.info("Fetching products with low stock");
        List<ProductResponseAdminDto> products = productRepository.findAll().stream()
                .filter(Product::isLowStock)
                .map(product -> productMapping.toResponsee(product))
                .collect(Collectors.toList());
        log.info("Fetched {} products with low stock", products.size());
        return products;
    }

    @Override
    public List<ProductResponseAdminDto> getCloseToExpirationProducts() {
        log.info("Fetching products close to expiration");
        List<ProductResponseAdminDto> products = productRepository.findAll().stream()
                .filter(Product::isCloseToExpiration)
                .map(product -> productMapping.toResponsee(product))
                .collect(Collectors.toList());
        log.info("Fetched {} products close to expiration", products.size());
        return products;
    }
}

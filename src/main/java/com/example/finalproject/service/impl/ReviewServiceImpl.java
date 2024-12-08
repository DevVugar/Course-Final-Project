package com.example.finalproject.service.impl;

import com.example.finalproject.exception.NotFoundException;
import com.example.finalproject.mapping.ReviewMapping;
import com.example.finalproject.model.dto.request.ReviewRequestDto;
import com.example.finalproject.model.dto.response.ReviewResponseDto;
import com.example.finalproject.model.entity.Product;
import com.example.finalproject.model.entity.Review;
import com.example.finalproject.model.entity.User;
import com.example.finalproject.repository.ProductRepository;
import com.example.finalproject.repository.ReviewRepository;
import com.example.finalproject.repository.UserRepository;
import com.example.finalproject.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    private final ReviewMapping reviewMapping;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;


    @Override
    public ReviewResponseDto add(ReviewRequestDto reviewRequestDto) {
        log.info("Adding a new review");
        Review review = reviewMapping.toEntity(reviewRequestDto);

        User user = userRepository.findById(reviewRequestDto.getUserId()).orElseThrow(() -> new NotFoundException("Not found user"));
        Product product = productRepository.findById(reviewRequestDto.getProductId()).orElseThrow(() -> new NotFoundException("Not found product"));
        review.setUser(user);
        review.setProduct(product);
        review.setUpdateAt(LocalDateTime.now());

        Review ans = reviewRepository.save(review);

        log.info("Review added");
        return reviewMapping.toResponse(ans);
    }

    @Override
    public ReviewResponseDto update(Long id, ReviewRequestDto reviewRequestDto) {
        log.info("Updating review");
        Review review = reviewRepository.findById(id).orElseThrow(() -> new NotFoundException("not found review"));

        reviewMapping.toUpdate(reviewRequestDto, review);

        review.setUpdateAt(LocalDateTime.now());

        Review ans=reviewRepository.save(review);

        log.info("Review updated");
        return reviewMapping.toResponse(ans);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting review");
        reviewRepository.deleteById(id);
        log.info("Review deleted");

    }

    @Override
    public List<ReviewResponseDto> getAll() {
        log.info("Fetching all reviews");
        List<ReviewResponseDto> reviews = reviewRepository.findAll().stream()
                .map(reviewMapping::toResponse)
                .collect(Collectors.toList());
        log.info("Fetched all reviews");
        return reviews;
    }
}

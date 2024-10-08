package com.example.finalproject.service.impl;

import com.example.finalproject.exception.ReviewNotFoundException;
import com.example.finalproject.mapping.ReviewMapping;
import com.example.finalproject.model.dto.request.ReviewRequestDto;
import com.example.finalproject.model.dto.response.ReviewResponseDto;
import com.example.finalproject.model.entity.Review;
import com.example.finalproject.repository.ReviewRepository;
import com.example.finalproject.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    private final ReviewMapping reviewMapping;

    @Override
    public ReviewResponseDto getById(Long id) {
        return reviewMapping.toResponse(reviewRepository.findById(id).orElseThrow(() ->
                new ReviewNotFoundException("Review not found")));
    }

    @Override
    public ReviewResponseDto add(ReviewRequestDto reviewRequestDto) {
        Review review = reviewMapping.toEntity(reviewRequestDto);

        reviewRepository.save(review);

        return reviewMapping.toResponse(review);
    }

    @Override
    public ReviewResponseDto update(ReviewRequestDto reviewRequestDto) {
        Review review = reviewMapping.toEntity(reviewRequestDto);

        reviewRepository.save(review);

        return reviewMapping.toResponse(review);
    }

    @Override
    public void delete(Long id) {
        reviewRepository.deleteById(id);

    }

    @Override
    public List<ReviewResponseDto> getAll() {
        return reviewRepository.findAll().stream().map((review)->
                reviewMapping.toResponse(review)).collect(Collectors.toList());
    }
}

package com.example.finalproject.service;

import com.example.finalproject.model.dto.request.ReviewRequestDto;
import com.example.finalproject.model.dto.response.ReviewResponseDto;
import com.example.finalproject.model.dto.response.UserResponseDto;

import java.util.List;

public interface ReviewService {


    ReviewResponseDto add(ReviewRequestDto reviewRequestDto);

    ReviewResponseDto update(Long id,ReviewRequestDto reviewRequestDto);

    void delete(Long id);

    List<ReviewResponseDto> getAll();

}

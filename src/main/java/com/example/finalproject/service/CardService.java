package com.example.finalproject.service;

import com.example.finalproject.model.dto.request.CardRequestDto;
import com.example.finalproject.model.dto.response.CardResponseDto;

public interface CardService {

    CardResponseDto add(Long id, CardRequestDto requestDto);

    CardResponseDto getById(Long id);


    void delete(Long id);

}

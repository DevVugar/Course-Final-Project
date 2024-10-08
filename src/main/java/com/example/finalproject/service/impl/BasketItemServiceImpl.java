package com.example.finalproject.service.impl;

import com.example.finalproject.exception.BasketNotFoundException;
import com.example.finalproject.mapping.BasketMapping;
import com.example.finalproject.model.dto.request.BasketRequestDto;
import com.example.finalproject.model.dto.response.BasketResponseDto;
import com.example.finalproject.model.entity.Basket;
import com.example.finalproject.repository.BasketRepository;
import com.example.finalproject.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BasketItemServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final BasketMapping basketMapping;

    @Override
    public BasketResponseDto add(BasketRequestDto requestDto) {
        Basket orderItem = basketMapping.toEntity(requestDto);

        Basket ans = basketRepository.save(orderItem);

        return basketMapping.toResponse(ans);
    }

    @Override
    public BasketResponseDto getById(Long id) {
        return basketMapping.toResponse(basketRepository.findById(id).orElseThrow(()->
                new BasketNotFoundException("Basket Not found")));
    }

    @Override
    public BasketResponseDto update(BasketRequestDto requestDto) {
        Basket orderItem = basketMapping.toEntity(requestDto);

        Basket ans = basketRepository.save(orderItem);

        return basketMapping.toResponse(ans);
    }

    @Override
    public void delete(Long id) {
      basketRepository.deleteById(id);
    }

    @Override
    public List<BasketResponseDto> getAll() {
        return basketRepository.findAll().stream().map(orderItem ->
                basketMapping.toResponse(orderItem)).collect(Collectors.toList());
    }
}

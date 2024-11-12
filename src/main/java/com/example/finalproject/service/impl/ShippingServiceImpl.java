package com.example.finalproject.service.impl;

import com.example.finalproject.exception.NotFoundException;
import com.example.finalproject.mapping.ShippingMapping;
import com.example.finalproject.model.dto.request.ShippingRequestDto;
import com.example.finalproject.model.dto.response.ShippingResponseDto;
import com.example.finalproject.model.entity.Shipping;
import com.example.finalproject.repository.ShippingRepository;
import com.example.finalproject.service.ShippingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShippingServiceImpl implements ShippingService {

    private final ShippingMapping shippingMapping;
    private final ShippingRepository shippingRepository;

    @Override
    public ShippingResponseDto add(ShippingRequestDto requestDto) {
        Shipping shipping = shippingMapping.toEntity(requestDto);
        Shipping ans = shippingRepository.save(shipping);

        return shippingMapping.toResponse(ans);
    }

    @Override
    public ShippingResponseDto getById(Long id) {
        return shippingMapping.toResponse(shippingRepository.findById(id).orElseThrow(()->
                new NotFoundException("Shipping not found")));
    }

    @Override
    public ShippingResponseDto update(ShippingRequestDto requestDto) {
        Shipping shipping = shippingMapping.toEntity(requestDto);
        Shipping ans = shippingRepository.save(shipping);

        return shippingMapping.toResponse(ans);
    }

    @Override
    public void delete(Long id) {
        shippingRepository.deleteById(id);
    }

    @Override
    public List<ShippingResponseDto> getAll() {
        return shippingRepository.findAll().stream().map(shipping->
                shippingMapping.toResponse(shipping)).collect(Collectors.toList());
    }
}

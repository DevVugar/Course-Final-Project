package com.example.finalproject.service.impl;

import com.example.finalproject.exception.OrderNotFoundException;
import com.example.finalproject.mapping.OrderMapping;
import com.example.finalproject.mapping.UserMapping;
import com.example.finalproject.model.dto.request.OrderRequestDto;
import com.example.finalproject.model.dto.response.OrderResponseDto;
import com.example.finalproject.model.entity.Order;
import com.example.finalproject.repository.OrderRepository;
import com.example.finalproject.repository.UserRepository;
import com.example.finalproject.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final OrderMapping orderMapping;

    @Override
    public OrderResponseDto add(OrderRequestDto orderDto) {
        Order order = orderMapping.toEntity(orderDto);

        orderRepository.save(order);

        return orderMapping.toResponse(order);
    }

    @Override
    public OrderResponseDto getById(Long id) {
        return   orderMapping.toResponse(
                 orderRepository.findById(id).orElseThrow(()->
                        new OrderNotFoundException("Order Not Found")));
    }

    @Override
    public OrderResponseDto update(OrderRequestDto orderDto) {
        Order order = orderMapping.toEntity(orderDto);

        orderRepository.save(order);

        return orderMapping.toResponse(order);
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<OrderResponseDto> getAll() {
        return orderRepository.findAll().stream().map((order)->orderMapping.toResponse(order)).
                collect(Collectors.toList());
    }
}

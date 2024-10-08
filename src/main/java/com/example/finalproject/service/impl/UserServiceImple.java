package com.example.finalproject.service.impl;

import com.example.finalproject.exception.BrandNotFoundException;
import com.example.finalproject.exception.UserNotFoundException;
import com.example.finalproject.mapping.OrderMapping;
import com.example.finalproject.mapping.UserMapping;
import com.example.finalproject.model.dto.request.UserRequestDto;
import com.example.finalproject.model.dto.response.BrandResponseDto;
import com.example.finalproject.model.dto.response.OrderResponseDto;
import com.example.finalproject.model.dto.response.UserResponseDto;
import com.example.finalproject.model.entity.Brand;
import com.example.finalproject.model.entity.Order;
import com.example.finalproject.model.entity.User;
import com.example.finalproject.repository.OrderRepository;
import com.example.finalproject.repository.UserRepository;
import com.example.finalproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImple implements UserService {


    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final UserMapping userMapping;
    private final OrderRepository orderRepository;
private final OrderMapping orderMapping;


    @Override
    public UserResponseDto add(UserRequestDto userDto) {
        User user = modelMapper.map(userDto, User.class);

        userRepository.save(user);

        return modelMapper.map(user, UserResponseDto.class);
    }

    @Override
    public UserResponseDto getById(Long id) {
        return modelMapper.map(userRepository.findById(id).
                orElseThrow(() -> new UserNotFoundException("User not found")), UserResponseDto.class);

    }

    @Override
    public UserResponseDto update(UserRequestDto userDto) {

        User user = modelMapper.map(userDto, User.class);

        userRepository.save(user);

        return modelMapper.map(user, UserResponseDto.class);
    }

    @Override
    public void delete(Long id) {
      userRepository.deleteById(id);
    }

    @Override
    public List<UserResponseDto> getAll() {
        return userRepository.findAll().stream().map((user)-> userMapping.toResponse(user)).collect(Collectors.toList());
    }

    @Override
    public List<OrderResponseDto> getOrders(Long id) {
      User user=userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User not found"));
      List<OrderResponseDto> orders=orderRepository.findByUser(user).stream().map(order->
              orderMapping.toResponse(order)).collect(Collectors.toList());


        return null;
    }
}

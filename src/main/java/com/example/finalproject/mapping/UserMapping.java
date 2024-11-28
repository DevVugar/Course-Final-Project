package com.example.finalproject.mapping;

import com.example.finalproject.model.dto.ProductDto;
import com.example.finalproject.model.dto.UserDto;
import com.example.finalproject.model.dto.request.UserRequestDto;
import com.example.finalproject.model.dto.response.UserResponseDto;
import com.example.finalproject.model.entity.Payment;
import com.example.finalproject.model.entity.Product;
import com.example.finalproject.model.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = {CartMapping.class,ReviewMapping.class,
                                           BasketMapping.class, WishListMapping.class}
        ,nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

public interface UserMapping {
    User toEntity(UserRequestDto userRequestDto);
    UserResponseDto toResponse(User user);

    void toUpdate(UserDto userDto, @MappingTarget User user);

}

package com.example.finalproject.mapping;

import com.example.finalproject.model.dto.ProductDto;
import com.example.finalproject.model.dto.request.UserRequestDto;
import com.example.finalproject.model.dto.response.UserResponseDto;
import com.example.finalproject.model.entity.Product;
import com.example.finalproject.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapping {
    User toEntity(UserRequestDto userRequestDto);
    UserResponseDto toResponse(User user);

    void toUpdate(UserRequestDto productDto, @MappingTarget User user);


}

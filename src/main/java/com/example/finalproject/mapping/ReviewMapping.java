package com.example.finalproject.mapping;


import com.example.finalproject.model.dto.ProductDto;
import com.example.finalproject.model.dto.request.ProductRequestDto;
import com.example.finalproject.model.dto.request.ReviewRequestDto;
import com.example.finalproject.model.dto.request.UserRequestDto;
import com.example.finalproject.model.dto.response.ReviewResponseDto;
import com.example.finalproject.model.entity.Product;
import com.example.finalproject.model.entity.Review;
import com.example.finalproject.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",uses = {ProductMapping.class, UserMapping.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

public interface ReviewMapping {

    Review toEntity(ReviewRequestDto reviewRequestDto);


    ReviewResponseDto toResponse(Review review);

    void toUpdate(ReviewRequestDto productDto, @MappingTarget Review review);

}

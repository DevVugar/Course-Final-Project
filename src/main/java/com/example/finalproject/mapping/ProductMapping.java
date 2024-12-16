package com.example.finalproject.mapping;

import com.example.finalproject.model.dto.ProductDto;
import com.example.finalproject.model.dto.request.ProductRequestDto;
import com.example.finalproject.model.dto.response.ProductResponseAdminDto;
import com.example.finalproject.model.dto.response.ProductResponseDto;
import com.example.finalproject.model.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.lang.annotation.Target;
import java.util.List;

@Mapper(componentModel = "spring", uses = {BrandMapping.class, CategoryMapping.class,
        ReviewMapping.class, WishListMapping.class, BasketMapping.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapping {

    ProductResponseDto toResponse(Product product);
    ProductDto toResponseDto(Product product);
    ProductResponseAdminDto toResponsee(Product product);

    List<ProductResponseDto> toResponse(List<Product> products);

    Product toEntity(ProductRequestDto productDto);


    void toUpdate(ProductRequestDto productDto, @MappingTarget Product product);


}

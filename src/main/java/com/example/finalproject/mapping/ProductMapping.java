package com.example.finalproject.mapping;

import com.example.finalproject.model.dto.ProductDto;
import com.example.finalproject.model.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {BrandMapping.class, CategoryMapping.class})
public interface ProductMapping {

    ProductDto getProductDto(Product product);

}

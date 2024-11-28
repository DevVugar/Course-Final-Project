package com.example.finalproject.service.impl;

import com.example.finalproject.exception.NotFoundException;
import com.example.finalproject.mapping.BrandMapping;
import com.example.finalproject.model.dto.request.BrandRequestDto;
import com.example.finalproject.model.dto.response.BrandResponseDto;
import com.example.finalproject.model.dto.response.ProductResponseDto;
import com.example.finalproject.model.entity.Brand;
import com.example.finalproject.model.entity.Product;
import com.example.finalproject.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.example.finalproject.repository.BrandRepository;
import com.example.finalproject.service.BrandService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapping brandMapping;
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    @Override

    public List<ProductResponseDto> getProductsByBrandId(Long brandId) {
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new NotFoundException("Brand not found with id: " + brandId));

        Collection<Product> products=productRepository.findByBrand(brand);

        return products.stream()
                .map(product -> modelMapper.map(product, ProductResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public BrandResponseDto add(BrandRequestDto brandDto) {

        Brand brand = modelMapper.map(brandDto, Brand.class);

        brandRepository.save(brand);

        return modelMapper.map(brand, BrandResponseDto.class);
    }

    @Override
    public BrandResponseDto getById(Long id) {

        return modelMapper.map(brandRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Brand not found")), BrandResponseDto.class);
    }

    @Override
    public BrandResponseDto update(BrandRequestDto brandDto) {
        Brand brand = modelMapper.map(brandDto, Brand.class);

        brandRepository.save(brand);

        return modelMapper.map(brand, BrandResponseDto.class);
    }

    @Override
    public void delete(Long id) {
        brandRepository.deleteById(id);
    }

    @Override
    public List<BrandResponseDto> getAll() {
        return brandRepository.findAll().stream().map(brand ->
                modelMapper.map(brand, BrandResponseDto.class)).collect(Collectors.toList());


    }

//    @Override
//    public ResponseEntity<?> get(Long id) {
//        Brand brand = brandRepository.findById(id).orElseThrow(() -> new RuntimeException("Brand not found"));
//        if(id > 1){
//            return ResponseEntity.ok(brandMapping.toEntity(brand));
//        }else {
//            return ResponseEntity.ok("ok");
//        }
//    }
}

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
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.example.finalproject.repository.BrandRepository;
import com.example.finalproject.service.BrandService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapping brandMapping;
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    @Override
    public List<ProductResponseDto> getProductsByBrandId(Long brandId) {
        log.info("ActionLog.getProductsByBrandId.started: brandId {}", brandId);


        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new NotFoundException("Brand not found with id: " + brandId));

        Collection<Product> products=productRepository.findByBrand(brand);


        log.info("ActionLog.getProductsByBrandId.productsRetrieved: brandId {}, productCount {}", brandId, products.size());
        return products.stream()
                .map(product -> modelMapper.map(product, ProductResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public BrandResponseDto add(BrandRequestDto brandDto) {
        log.info("ActionLog.addBrand.started: brandName {}", brandDto.getName());

        Brand brand = modelMapper.map(brandDto, Brand.class);

        brandRepository.save(brand);


        log.info("ActionLog.addBrand.completed: brandId {}", brand.getId());
        return modelMapper.map(brand, BrandResponseDto.class);
    }

    @Override
    public BrandResponseDto getById(Long id) {
        log.info("ActionLog.getBrandById.started: brandId {}", id);

        BrandResponseDto response = modelMapper.map(
                brandRepository.findById(id)
                        .orElseThrow(() -> {
                            log.error("ActionLog.getBrandById.brandNotFound: brandId {}", id);
                            return new NotFoundException("Brand not found");
                        }),
                BrandResponseDto.class);

        log.info("ActionLog.getBrandById.completed: brandId {}", id);
        return response;
    }


    @Override
    public BrandResponseDto update(BrandRequestDto brandDto) {
        log.info("ActionLog.updateBrand.started: brandId {}", brandDto.getName());
        Brand brand = modelMapper.map(brandDto, Brand.class);

        brandRepository.save(brand);

        log.info("ActionLog.updateBrand.completed: brandId {}", brand.getId());
        return modelMapper.map(brand, BrandResponseDto.class);
    }

    @Override
    public void delete(Long id) {
        log.info("ActionLog.deleteBrand.started: brandId {}", id);
        brandRepository.deleteById(id);
        log.info("ActionLog.deleteBrand.completed: brandId {}", id);
    }

    @Override
    public List<BrandResponseDto> getAll() {
        log.info("ActionLog.getAllBrands.started");
        List<BrandResponseDto> brands = brandRepository.findAll()
                .stream()
                .map(brand -> modelMapper.map(brand, BrandResponseDto.class))
                .collect(Collectors.toList());

        log.info("ActionLog.getAllBrands.completed: totalBrands {}", brands.size());
        return brands;

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

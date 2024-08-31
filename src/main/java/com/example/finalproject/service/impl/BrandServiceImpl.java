package com.example.finalproject.service.impl;

import com.example.finalproject.mapping.BrandMapping;
import com.example.finalproject.model.dto.BrandDto;
import com.example.finalproject.model.entity.Brand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.finalproject.repository.BrandRepository;
import com.example.finalproject.service.BrandService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapping brandMapping;

    @Override
    public BrandDto add(Brand brand) {
        brandRepository.save(brand);

        return brandMapping.toBranDto(brand);

    }

    @Override
    public BrandDto getById(Long id) {
        return brandMapping.toBranDto(brandRepository.findById(id).orElseThrow(RuntimeException::new));
    }

    @Override
    public BrandDto update(Brand brand) {
        brandRepository.save(brand);

        return brandMapping.toBranDto(brand);
    }

    @Override
    public void delete(Long id) {
        brandRepository.deleteById(id);
    }

    @Override
    public List<BrandDto> getAll() {

        return brandMapping.toBranDto(brandRepository.findAll());
    }
}

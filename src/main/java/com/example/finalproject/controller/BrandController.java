package com.example.finalproject.controller;

import com.example.finalproject.model.dto.BrandDto;
import com.example.finalproject.model.entity.Brand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.finalproject.service.BrandService;

import java.util.List;

@RestController
@RequestMapping("/brand")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @PostMapping
    public BrandDto add(@RequestBody Brand brand) {
        return brandService.add(brand);
    }

    @GetMapping("/{id}")
    public BrandDto getById(@PathVariable Long id) {
        return brandService.getById(id);
    }

    @PutMapping
    public BrandDto update(@RequestBody Brand brand) {
        return brandService.update(brand);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        brandService.delete(id);
    }

    @GetMapping
    public List<BrandDto> getAll() {
        return brandService.getAll();
    }
}


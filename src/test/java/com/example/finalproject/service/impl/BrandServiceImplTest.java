package com.example.finalproject.service.impl;

import com.example.finalproject.model.dto.request.BrandRequestDto;
import com.example.finalproject.model.dto.response.BrandResponseDto;
import com.example.finalproject.model.entity.Brand;
import com.example.finalproject.repository.BrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BrandServiceImplTest {

    @InjectMocks
    private BrandServiceImpl brandService;

    @Mock
    private BrandRepository brandRepository;
    @Mock
    private ModelMapper modelMapper;

    private Brand brand;
    private BrandResponseDto brandResponseDto;

    private BrandRequestDto brandRequestDto;

    private List<Brand> brandList;



    @BeforeEach
    void setUp() {

        brand = new Brand();
        brand.setId(1L);
        brand.setName("Brand");


        brandResponseDto = new BrandResponseDto();
        brandResponseDto.setId(1L);
        brandResponseDto.setName("Brand");

        brandRequestDto = new BrandRequestDto();
        brandRequestDto.setId(1L);
        brandRequestDto.setName("Brand");


        brandList=new ArrayList<>();
        brandList.add(brand);
    }

    @Test
    void findById() {

        //Arrange
        when(brandRepository.findById(anyLong())).thenReturn(Optional.of(brand));
        when(modelMapper.map(brand, BrandResponseDto.class)).thenReturn(brandResponseDto);

        // Act
        BrandResponseDto brandDto = brandService.getById(1L);

        // Assert
        assertEquals(1L, brandDto.getId());
        assertEquals("Brand", brandDto.getName());

        // Verify
        verify(brandRepository, times(1)).findById(1L);
    }



    @Test
    void add(){
        when(brandRepository.save(any())).thenReturn(brand);
        when(modelMapper.map(brandRequestDto, Brand.class)).thenReturn(brand);
        when(modelMapper.map(brand, BrandResponseDto.class)).thenReturn(brandResponseDto);


        BrandResponseDto brandDto=brandService.add(brandRequestDto);

        assertEquals(1l,brandDto.getId());
        assertEquals("Brand",brandDto.getName());

        verify(brandRepository,times(1)).save(brand);
    }

    @Test
    void delete(){

       brandRepository.deleteById(anyLong());


        verify(brandRepository,times(1)).deleteById(anyLong());
    }

    @Test
    void getAll(){
        when(brandRepository.findAll()).thenReturn(brandList);

        List<Brand> brands= brandRepository.findAll();

        assertEquals("Brand",brands.get(0).getName());

        verify(brandRepository,times(1)).findAll();

    }
}

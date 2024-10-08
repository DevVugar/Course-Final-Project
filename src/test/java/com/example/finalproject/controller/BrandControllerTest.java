package com.example.finalproject.controller;


import com.example.finalproject.model.dto.response.BrandResponseDto;
import com.example.finalproject.service.BrandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;


@ExtendWith(MockitoExtension.class)
class BrandControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private BrandController brandController;

    @Mock
    private BrandService brandService;

    private BrandResponseDto brandResponseDto;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(brandController).build();

        brandResponseDto = new BrandResponseDto();
        brandResponseDto.setId(1L);
        brandResponseDto.setName("Brand");
    }


    @Test
    void getById() throws Exception {

        when(brandService.getById(anyLong())).thenReturn(brandResponseDto);

        mockMvc.perform(get("/brand/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Brand")));
    }
    }

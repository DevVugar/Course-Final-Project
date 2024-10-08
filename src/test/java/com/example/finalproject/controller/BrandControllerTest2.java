package com.example.finalproject.controller;


import com.example.finalproject.model.dto.response.BrandResponseDto;
import com.example.finalproject.service.BrandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BrandControllerTest2 {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BrandService brandService;

    private BrandResponseDto brandDto;

    @BeforeEach
    void setUp() {
        brandDto = new BrandResponseDto();
        brandDto.setId(1L);
        brandDto.setName("Brand");

    }

//    @AfterEach
//    void tearDown() {
//        brandDto=null;
//        brandService=null;
//    }

    @Test
    void getById() throws Exception {
        when(brandService.getById(anyLong())).thenReturn(brandDto);

        mockMvc.perform(get("/brand/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Brand")));
    }
    }





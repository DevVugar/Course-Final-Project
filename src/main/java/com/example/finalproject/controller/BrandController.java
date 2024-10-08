package com.example.finalproject.controller;

import com.example.finalproject.model.dto.request.BrandRequestDto;
import com.example.finalproject.model.dto.response.BrandResponseDto;
import com.example.finalproject.model.dto.response.ProductResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.finalproject.service.BrandService;

import java.util.List;


@RestController
@RequestMapping("/brand")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;


    @Operation(summary = "Get Products by Brand")
    @GetMapping("/{id}/getProducts")
    public ResponseEntity<List<ProductResponseDto>> getProductByBrand(@PathVariable Long id){

        List<ProductResponseDto> products = brandService.getProductsByBrandId(id);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


    @Operation(summary = "Adding for Brand")
    @PostMapping
    public ResponseEntity<BrandResponseDto> add(@RequestBody BrandRequestDto brandDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(brandService.add(brandDto));

        //return new ResponseEntity<>(brandService.add(brandDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Get only one Brand with id")
    @GetMapping("/{id}")
    public ResponseEntity<BrandResponseDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(brandService.getById(id), HttpStatus.OK);
    }

    @Operation(summary = "Update Brand")
    @PutMapping
    public ResponseEntity<BrandResponseDto> update(@RequestBody BrandRequestDto brandDto) {
        return new ResponseEntity<>(brandService.add(brandDto), HttpStatus.OK);

    }



    @Operation(summary = "Delete Brand")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        brandService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "get all Brands")
    @GetMapping
    public ResponseEntity<List<BrandResponseDto>> getAll() {
        return ResponseEntity.ok(brandService.getAll());
    }
}



//@GetMapping("/a/{id}")
//public ResponseEntity<?> get(@PathVariable Long id){
//    return ResponseEntity.ok(brandService.get(id));
//}

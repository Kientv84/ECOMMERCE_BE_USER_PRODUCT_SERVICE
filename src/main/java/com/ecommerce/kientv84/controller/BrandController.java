package com.ecommerce.kientv84.controller;

import com.ecommerce.kientv84.dtos.request.BrandRequest;
import com.ecommerce.kientv84.dtos.request.BrandUpdateRequest;
import com.ecommerce.kientv84.dtos.response.BrandResponse;
import com.ecommerce.kientv84.mappers.BrandMapper;
import com.ecommerce.kientv84.services.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/api")
@RequiredArgsConstructor
public class BrandController {
    private final BrandService brandService;

    @GetMapping("/brands")
    public ResponseEntity<List<BrandResponse>> getALlBrand() {
        return ResponseEntity.ok(brandService.getAllBrand());
    }

    @PostMapping("/brand")
    public ResponseEntity<BrandResponse> createBrand(@Valid @RequestBody BrandRequest brandRequest) {
        return ResponseEntity.ok(brandService.createBrand(brandRequest));
    }

    @GetMapping("/brand/{id}")
    public ResponseEntity<BrandResponse> getBrandById(@PathVariable UUID id) {
        return ResponseEntity.ok(brandService.getBrandById(id));
    }

    @PostMapping("/brand/{id}")
    public ResponseEntity<BrandResponse> updateBrandById(@PathVariable UUID id, @RequestBody BrandUpdateRequest updateData) {
        return ResponseEntity.ok(brandService.updateBrandById(id, updateData));
    }

    @PostMapping("/brands")
    public String deleteBrand( @RequestBody List<UUID> uuids) {
        return brandService.deleteBrands(uuids);
    }

}

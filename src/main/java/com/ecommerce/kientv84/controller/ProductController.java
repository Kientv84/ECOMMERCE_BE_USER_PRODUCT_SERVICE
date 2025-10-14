package com.ecommerce.kientv84.controller;

import com.ecommerce.kientv84.dtos.request.ProductRequest;
import com.ecommerce.kientv84.dtos.request.ProductUpdateRequest;
import com.ecommerce.kientv84.dtos.request.UserRequest;
import com.ecommerce.kientv84.dtos.response.ProductResponse;
import com.ecommerce.kientv84.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/items")
    public ResponseEntity<List<ProductResponse>> getAllProduct() {
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @PostMapping("/item")
    public ResponseEntity<ProductResponse> createProduct(@Valid  @RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok(productService.createProduct(productRequest));
    }

    @GetMapping("/item/{uuid}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable UUID uuid) {
        return ResponseEntity.ok(productService.getProductById(uuid));
    }

    @PostMapping("/item/{uuid}")
    public ResponseEntity<ProductResponse> updateProductById(@PathVariable UUID uuid, @Valid @RequestBody ProductUpdateRequest updateData) {
        return ResponseEntity.ok(productService.updateProductById(uuid, updateData));
    }

    @PostMapping("/items")
    public ResponseEntity<String> updateProductById( @RequestBody List<UUID> uuids) {
        return ResponseEntity.ok(productService.deleteProduct(uuids));
    }

}

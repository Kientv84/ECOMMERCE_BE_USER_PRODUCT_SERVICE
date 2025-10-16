package com.ecommerce.kientv84.controller;

import com.ecommerce.kientv84.dtos.request.CategoryRequest;
import com.ecommerce.kientv84.dtos.request.CategoryUpdateRequest;
import com.ecommerce.kientv84.dtos.response.CategoryResponse;
import com.ecommerce.kientv84.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryResponse>> getAllCategory() {
        return ResponseEntity.ok(categoryService.getAllCategory());
    }

    @PostMapping("/category")
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        return ResponseEntity.ok(categoryService.crateCategory(categoryRequest));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable UUID id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @PostMapping("/category/{id}")
    public ResponseEntity<CategoryResponse> updateCategoryById(@PathVariable UUID id, @RequestBody CategoryUpdateRequest updateData) {
        return ResponseEntity.ok(categoryService.updateCategoryById(id, updateData));
    }

    @PostMapping("/categories")
    public String updateCategoryById(@RequestBody List<UUID> ids) {
        return categoryService.deleteCategories(ids);
    }
}

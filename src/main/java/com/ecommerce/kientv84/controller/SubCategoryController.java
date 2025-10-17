package com.ecommerce.kientv84.controller;

import com.ecommerce.kientv84.dtos.request.SubCategoryRequest;
import com.ecommerce.kientv84.dtos.request.SubCategoryUpdateRequest;
import com.ecommerce.kientv84.dtos.response.SubCategoryResponse;
import com.ecommerce.kientv84.services.SubCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/api")
@RequiredArgsConstructor
public class SubCategoryController {
    private final SubCategoryService subCategoryService;

    @GetMapping("/sub_categories")
    public ResponseEntity<List<SubCategoryResponse>> getAllSubCategory() {
        return ResponseEntity.ok(subCategoryService.getAllSubCategory());
    }

    @PostMapping("/sub_category")
    public ResponseEntity<SubCategoryResponse> createSubCategory(@Valid @RequestBody SubCategoryRequest request) {
        return ResponseEntity.ok(subCategoryService.createSubCategory(request));
    }

    @GetMapping("/sub_category/{id}")
    public ResponseEntity<SubCategoryResponse> getSubCategoryById(@PathVariable UUID id) {
        return ResponseEntity.ok(subCategoryService.getSubCategoryById(id));
    }

    @PostMapping("/sub_category/{id}")
    public ResponseEntity<SubCategoryResponse> updateSubCategoryById(@PathVariable UUID id, @Valid @RequestBody SubCategoryUpdateRequest updateData) {
        return ResponseEntity.ok(subCategoryService.updateSubCategoryById(id, updateData));
    }

    @PostMapping("/sub_categories")
    public String deleteSubCategory(@RequestBody List<UUID> uuids) {
        return subCategoryService.deleteSubCategories(uuids);
    }
}

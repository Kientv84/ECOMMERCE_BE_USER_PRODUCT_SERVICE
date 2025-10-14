package com.ecommerce.kientv84.services.impls;

import com.ecommerce.kientv84.commons.EnumError;
import com.ecommerce.kientv84.commons.StatusEnum;
import com.ecommerce.kientv84.dtos.request.ProductRequest;
import com.ecommerce.kientv84.dtos.request.ProductUpdateRequest;
import com.ecommerce.kientv84.dtos.response.ProductResponse;
import com.ecommerce.kientv84.entites.ProductEntity;
import com.ecommerce.kientv84.exceptions.ServiceException;
import com.ecommerce.kientv84.mappers.ProductMapper;
import com.ecommerce.kientv84.respositories.ProductRepository;
import com.ecommerce.kientv84.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductServiceImp implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductResponse> getAllProduct() {
        try {

            List<ProductResponse> items = productRepository.findAll().stream().map(item -> productMapper.mapToProductResponse(item)).toList();

            return items;

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(EnumError.PRO_ERR_GET, "product.get.error" );
        }
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        try {
            String generatedName = generateNameProduct(productRequest);

            ProductEntity productEntity = ProductEntity.builder()
                    .productName(generatedName)
                    .brand(productRequest.getBrand())
                    .category(productRequest.getCategory())
                    .subCategory(productRequest.getSubCategory())
                    .basePrice(productRequest.getBasePrice())
                    .status(productRequest.getStatus())
                    .createdBy("ADMIN")
                    .createdDate(new Date())
                    .build();

            productRepository.save(productEntity);

            return productMapper.mapToProductResponse(productEntity);
        } catch (ServiceException e) {
            throw e;
        }
        catch (Exception e) {
            throw new ServiceException(EnumError.PRO_ERR_GET, "product.get.error" );
        }
    }

    @Override
    public ProductResponse getProductById(UUID uuid) {
        try {
            ProductEntity productEntity = productRepository.findById(uuid).orElseThrow(() -> new ServiceException(EnumError.PRO_ERR_GET, "product.get.error", new Object[]{}));

            return productMapper.mapToProductResponse(productEntity);

        } catch (ServiceException e) {
            //các lỗi business (do bạn chủ động ném ra)
            throw e;
        } catch (Exception e) {
            // Bọc lại các lỗi hệ thống khác
            throw new ServiceException(EnumError.PRO_ERR_GET, "product.get.error");
        }
    }

    @Override
    public ProductResponse updateProductById(UUID uuid, ProductUpdateRequest updateData) {
        return null;
    }

    @Override
    public String deleteProduct(List<UUID> uuids) {
        return  null    ;
    }

    // SUB FUNCTION
    @Override
    public String generateNameProduct(ProductRequest productRequest) {
        return String.format("%s %s %s",
                productRequest.getBrand().trim(),
                productRequest.getCategory().trim(),
                productRequest.getSubCategory().trim()
        ).trim();
    }

}

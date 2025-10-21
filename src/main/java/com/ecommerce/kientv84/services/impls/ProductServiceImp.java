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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
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



            ProductEntity productEntity = ProductEntity.builder()
//                    .brand(productRequest.getBrand())
//                    .category(productRequest.getCategory())
                    .productCode(productRequest.getCode())
//                    .subCategory(productRequest.getSubCategory())
                    .basePrice(productRequest.getBasePrice())
                    .status(productRequest.getStatus())
                    .createdBy("ADMIN")
                    .createdDate(new Date())
                    .build();

            String generatedName = generateNameProduct(productEntity);

            productEntity.setProductName(generatedName);

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

        try {

            ProductEntity productEntity = productRepository.findById(uuid).orElseThrow(() -> new ServiceException(EnumError.PRO_ERR_GET, "product.get.error"));


           if ( updateData.getBrand() != null) {
               productEntity.setBrand(productEntity.getBrand());
           }
            if ( updateData.getCategory() != null) {
                productEntity.setCategory(productEntity.getCategory());
            }
            if ( updateData.getSubCategory() != null) {
                productEntity.setSubCategory(productEntity.getSubCategory());
            }
            if ( updateData.getBasePrice() != null) {
                productEntity.setBasePrice(productEntity.getBasePrice());
            }
            if ( updateData.getStatus() != null) {
                productEntity.setStatus(productEntity.getStatus());
            }

            String name = generateNameProduct(productEntity);

            productEntity.setProductName(name);

            productRepository.save(productEntity);

            return productMapper.mapToProductResponse(productEntity);


        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException(EnumError.INTERNAL_ERROR, "ACC-S-99");
        }
    }

    @Override
    public String deleteProduct(List<UUID> uuids) {
        try {

            if ( uuids == null && uuids.isEmpty()) {
                throw new ServiceException(
                        EnumError.PRO_ERR_DEL_EM,
                       "product.delete.empty",
                        new Object[]{}
                );
            }

            List<ProductEntity> products = productRepository.findAllById(uuids);

            Set<UUID> productSet = products.stream().map(ProductEntity::getId).collect(Collectors.toSet());

            List<UUID> notFoundIds = uuids.stream()
                    .filter(id -> !productSet.contains(id))
                    .toList();

            if (!notFoundIds.isEmpty()) {
                throw new ServiceException(
                        EnumError.PRO_ERR_NOT_FOUND,
                        "product.delete.notfound" + notFoundIds,
                        new Object[]{notFoundIds.toString()}
                );
            }

            productRepository.deleteAllById(uuids);
            log.info("Deleted products successfully: {}", uuids);

            return "Deleted products successfully: {}" + uuids;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // SUB FUNCTION
    @Override
    public String generateNameProduct(ProductEntity productEntity) {
        return null;
//        return String.format("%s %s %s",
////                productEntity.getBrand().trim()
////                productEntity.getCategory().trim(),
////                productEntity.getSubCategory().trim()
//        ).trim();
    }

}

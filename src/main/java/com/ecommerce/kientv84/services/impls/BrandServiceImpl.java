package com.ecommerce.kientv84.services.impls;

import com.ecommerce.kientv84.commons.EnumError;
import com.ecommerce.kientv84.dtos.request.BrandRequest;
import com.ecommerce.kientv84.dtos.request.BrandUpdateRequest;
import com.ecommerce.kientv84.dtos.response.BrandResponse;
import com.ecommerce.kientv84.entites.BrandEntity;
import com.ecommerce.kientv84.exceptions.ServiceException;
import com.ecommerce.kientv84.mappers.BrandMapper;
import com.ecommerce.kientv84.respositories.BrandRepository;
import com.ecommerce.kientv84.services.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BrandServiceImpl implements BrandService {
    private final BrandMapper brandMapper;
    private final BrandRepository brandRepository;

    @Override
    public List<BrandResponse> getAllBrand() {
        try {

            List<BrandResponse> responses = brandRepository.findAll().stream().map(brand -> brandMapper.mapToBrandResponse(brand)).toList();

            return responses;

        } catch (Exception e) {
            throw new ServiceException(EnumError.BRAND_ERR_GET, "brand.get.error");
        }
    }

    @Override
    public BrandResponse createBrand(BrandRequest brandRequest) {
        try {
            BrandEntity brand = brandRepository.findBrandByBrandCode(brandRequest.getBrandCode());

            if ( brand != null ) {
                throw new ServiceException(EnumError.BRAND_DATA_EXISTED, "brand.exit");
            }

            BrandEntity brandEntity = BrandEntity.builder()
                    .brandName(brandRequest.getBrandName())
                    .brandCode(brandRequest.getBrandCode())
                    .status(brandRequest.getStatus())
                    .description(brandRequest.getDescription())
                    .build();

            brandRepository.save(brandEntity);

            return brandMapper.mapToBrandResponse(brandEntity);

        } catch (ServiceException e) {
            throw e;
        } catch ( Exception e) {
            throw new ServiceException(EnumError.INTERNAL_ERROR, "sys.internal.error");
        }
    }

    @Override
    public BrandResponse getBrandById(UUID id) {
        try {
            BrandEntity brand = brandRepository.findById(id).orElseThrow(() -> new ServiceException(EnumError.BRAND_ERR_GET, "brand.get.error"));

            return  brandMapper.mapToBrandResponse(brand);

        } catch (ServiceException e) {
            throw e ;
        } catch (Exception e) {
            throw new ServiceException(EnumError.INTERNAL_ERROR, "sys.internal.error");
        }
    }

    @Override
    public BrandResponse updateBrandById(UUID id, BrandUpdateRequest updateData) {
        try {
            BrandEntity brand = brandRepository.findById(id).orElseThrow(() -> new ServiceException(EnumError.BRAND_ERR_GET, "brand.get.error"));

            if (updateData.getBrandName() != null) {
                brand.setBrandName(updateData.getBrandName());
            }
            if ( updateData.getBrandCode() != null) {
                brand.setBrandCode(updateData.getBrandCode());
            }
            if(updateData.getStatus() != null) {
                brand.setStatus(updateData.getStatus());
            }
            if(updateData.getDescription() != null) {
                brand.setDescription(updateData.getDescription());
            }

            brandRepository.save(brand);

            return  brandMapper.mapToBrandResponse(brand);

        } catch (ServiceException e) {
            throw e ;
        } catch (Exception e) {
            throw new ServiceException(EnumError.INTERNAL_ERROR, "sys.internal.error");
        }
    }

    @Override
    public String deleteBrands(List<UUID> uuids) {
        try {

            if ( uuids == null || uuids.isEmpty()) {
                throw new ServiceException(EnumError.BRAND_ERR_DEL_EM, "brand.delete.empty");
            }

            List<BrandEntity> brands = brandRepository.findAllById(uuids);

            if (brands.isEmpty()) {
                throw new ServiceException(EnumError.BRAND_ERR_NOT_FOUND, "brand.delete.notfound");
            }

//            Set<UUID> foundIds = brands.stream().map(BrandEntity::getId).collect(Collectors.toSet());
//
//            List<UUID> notFoundId = uuids.stream().filter(id -> !foundIds.contains(id)).toList();
//
//            if (!notFoundId.isEmpty()) {
//                throw new ServiceException(EnumError.BRAND_ERR_NOT_FOUND, "brand.delete.notfound");
//            }

            brandRepository.deleteAllById(uuids);

            return "Deleted categories successfully: {}" + uuids;

        } catch (Exception e) {
            throw new ServiceException(EnumError.INTERNAL_ERROR, "sys.internal.error");
        }
    }
}

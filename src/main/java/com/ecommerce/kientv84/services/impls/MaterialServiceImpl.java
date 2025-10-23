package com.ecommerce.kientv84.services.impls;

import com.ecommerce.kientv84.commons.EnumError;
import com.ecommerce.kientv84.dtos.request.MaterialRequest;
import com.ecommerce.kientv84.dtos.request.MaterialUpdateRequest;
import com.ecommerce.kientv84.dtos.response.CollectionResponse;
import com.ecommerce.kientv84.dtos.response.MaterialResponse;
import com.ecommerce.kientv84.entites.CollectionEntity;
import com.ecommerce.kientv84.entites.MaterialEntity;
import com.ecommerce.kientv84.exceptions.ServiceException;
import com.ecommerce.kientv84.mappers.MaterialMapper;
import com.ecommerce.kientv84.respositories.MaterialRepository;
import com.ecommerce.kientv84.services.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MaterialServiceImpl implements MaterialService {
    private final MaterialRepository materialRepository;
    private final MaterialMapper materialMapper;

    @Override
    public List<MaterialResponse> getAllMaterial() {
        try {
            List<MaterialResponse> responses = materialRepository.findAll().stream().map(material -> materialMapper.mapToMaterialResponse(material)).toList();

            return responses;
        } catch (Exception e) {
            throw new ServiceException(EnumError.COLLECTION_ERR_GET, "collection.get.error");
        }
    }

    @Override
    public MaterialResponse createMaterial(MaterialRequest request) {
        try {
            MaterialEntity material = materialRepository.findMaterialByMaterialCode(request.getMaterialCode());

            if(material != null) {
                throw new ServiceException(EnumError.MATERIAL_DATA_EXISTED, "material.exit");
            }

            MaterialEntity newMaterial = MaterialEntity.builder()
                    .materialCode(request.getMaterialCode())
                    .materialName(request.getMaterialName())
                    .status(request.getStatus())
                    .description(request.getDescription())
                    .build();

            materialRepository.save(newMaterial);

            return materialMapper.mapToMaterialResponse(newMaterial);

        } catch (ServiceException e) {
            throw e;
        } catch ( Exception e) {
            throw new ServiceException(EnumError.INTERNAL_ERROR, "sys.internal.error");
        }
    }

    @Override
    public MaterialResponse getMaterialById(UUID uuid) {
        try {
            MaterialEntity materialEntity = materialRepository.findById(uuid).orElseThrow(() -> new ServiceException(EnumError.MATERIAL_ERR_GET, "material.get.error"));

            return materialMapper.mapToMaterialResponse(materialEntity);
        } catch (ServiceException e) {
            throw e;
        } catch ( Exception e) {
            throw new ServiceException(EnumError.INTERNAL_ERROR, "sys.internal.error");
        }
    }

    @Override
    public MaterialResponse updateMaterial(UUID uuid, MaterialUpdateRequest updateData) {
        try {
            MaterialEntity materialEntity = materialRepository.findById(uuid).orElseThrow(() -> new ServiceException(EnumError.MATERIAL_ERR_GET, "material.get.error"));

            if ( updateData.getMaterialCode() != null) {
                materialEntity.setMaterialCode(updateData.getMaterialCode());
            }
            if ( updateData.getMaterialName() != null) {
                materialEntity.setMaterialName(updateData.getMaterialName());
            }
            if ( updateData.getStatus() != null) {
                materialEntity.setStatus(updateData.getStatus());
            }
            if ( updateData.getDescription() != null) {
                materialEntity.setDescription(updateData.getDescription());
            }

            materialRepository.save(materialEntity);

            return materialMapper.mapToMaterialResponse(materialEntity);

        } catch (ServiceException e) {
            throw e;
        } catch ( Exception e) {
            throw new ServiceException(EnumError.INTERNAL_ERROR, "sys.internal.error");
        }
    }

    @Override
    public String deleteMaterial(List<UUID> uuids) {
        try {
            if ( uuids == null || uuids.isEmpty()) {
                throw new ServiceException(EnumError.MATERIAL_ERR_DEL_EM, "material.delete.empty");
            }

            List<MaterialEntity> foundIds = materialRepository.findAllById(uuids);

            if ( foundIds.isEmpty()) {
                throw new ServiceException(EnumError.MATERIAL_ERR_NOT_FOUND, "material.delete.nottfound");
            }

            materialRepository.deleteAllById(uuids);

            return "Deleted materials successfully: {}" + uuids;

        } catch (Exception e) {
            throw new ServiceException(EnumError.INTERNAL_ERROR, "sys.internal.error");
        }
    }
}

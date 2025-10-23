package com.ecommerce.kientv84.services.impls;

import com.ecommerce.kientv84.commons.EnumError;
import com.ecommerce.kientv84.dtos.request.CollectionRequest;
import com.ecommerce.kientv84.dtos.request.CollectionUpdateRequest;
import com.ecommerce.kientv84.dtos.response.CollectionResponse;
import com.ecommerce.kientv84.entites.CollectionEntity;
import com.ecommerce.kientv84.exceptions.ServiceException;
import com.ecommerce.kientv84.mappers.CollectionMapper;
import com.ecommerce.kientv84.respositories.CollectionRepository;
import com.ecommerce.kientv84.services.CollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CollectionServiceImpl implements CollectionService {
    private final CollectionMapper collectionMapper;
    private final CollectionRepository collectionRepository;

    @Override
    public List<CollectionResponse> getAllCollection() {
        try {
        List<CollectionResponse> responses = collectionRepository.findAll().stream().map(col -> collectionMapper.mapToCollectionResponse(col)).toList();

        return responses;
        } catch (Exception e) {
            throw new ServiceException(EnumError.COLLECTION_ERR_GET, "collection.get.error");
        }
    }

    @Override
    public CollectionResponse createCollection(CollectionRequest request) {
        try {
            CollectionEntity collectionEntity = collectionRepository.findCollectionByCollectionCode(request.getCollectionCode());

            if(collectionEntity != null) {
                throw new ServiceException(EnumError.COLLECTION_DATA_EXISTED, "collection.exit");
            }

            CollectionEntity newCollection = CollectionEntity.builder()
                    .collectionCode(request.getCollectionCode())
                    .collectionName(request.getCollectionName())
                    .status(request.getStatus())
                    .description(request.getDescription())
                    .build();

            collectionRepository.save(newCollection);

            return collectionMapper.mapToCollectionResponse(newCollection);

        } catch (ServiceException e) {
            throw e;
        } catch ( Exception e) {
            throw new ServiceException(EnumError.INTERNAL_ERROR, "sys.internal.error");
        }
    }

    @Override
    public CollectionResponse getCollectionById(UUID uuid) {
        try {
            CollectionEntity collectionEntity = collectionRepository.findById(uuid).orElseThrow(() -> new ServiceException(EnumError.COLLECTION_ERR_GET, "collection.get.error"));

            return collectionMapper.mapToCollectionResponse(collectionEntity);
        } catch (ServiceException e) {
            throw e;
        } catch ( Exception e) {
            throw new ServiceException(EnumError.INTERNAL_ERROR, "sys.internal.error");
        }

    }

    @Override
    public CollectionResponse updateCollection(UUID uuid, CollectionUpdateRequest updateData) {
        try {
            CollectionEntity collectionEntity = collectionRepository.findById(uuid).orElseThrow(() -> new ServiceException(EnumError.COLLECTION_ERR_GET, "collection.get.error"));

            if ( updateData.getCollectionCode() != null) {
                collectionEntity.setCollectionCode(updateData.getCollectionCode());
            }
            if ( updateData.getCollectionName() != null) {
                collectionEntity.setCollectionName(updateData.getCollectionName());
            }
            if ( updateData.getStatus() != null) {
                collectionEntity.setStatus(updateData.getStatus());
            }
            if ( updateData.getDescription() != null) {
                collectionEntity.setDescription(updateData.getDescription());
            }

            collectionRepository.save(collectionEntity);

            return collectionMapper.mapToCollectionResponse(collectionEntity);

        } catch (ServiceException e) {
            throw e;
        } catch ( Exception e) {
            throw new ServiceException(EnumError.INTERNAL_ERROR, "sys.internal.error");
        }
    }

    @Override
    public String deleteCollection(List<UUID> uuids) {
        try {
            if ( uuids == null || uuids.isEmpty()) {
                throw new ServiceException(EnumError.COLLECTION_ERR_DEL_EM, "collection.delete.empty");
            }

            List<CollectionEntity> foundIds = collectionRepository.findAllById(uuids);

            if ( foundIds.isEmpty()) {
                throw new ServiceException(EnumError.COLLECTION_ERR_NOT_FOUND, "collection.delete.notfound");
            }

            collectionRepository.deleteAllById(uuids);

            return "Deleted collections successfully: {}" + uuids;

        } catch (Exception e) {
            throw new ServiceException(EnumError.INTERNAL_ERROR, "sys.internal.error");
        }
    }
}

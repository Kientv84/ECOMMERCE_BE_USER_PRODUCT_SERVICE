package com.ecommerce.kientv84.dtos.request.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class KafkaInventoryRequest {
    private UUID productId;
    private String productName;
    private Integer stock;
}

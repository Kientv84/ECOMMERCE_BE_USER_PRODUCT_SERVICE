package com.ecommerce.kientv84.messaging.producers;

import com.ecommerce.kientv84.dtos.request.kafka.KafkaInventoryRequest;
import com.ecommerce.kientv84.properties.KafkaTopicProperties;
import com.ecommerce.kientv84.services.KafkaService;
import com.ecommerce.kientv84.utils.KafkaObjectError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductProducerImpl implements ProductProducer{
    private final KafkaTopicProperties kafkaTopicProperties;
    private final KafkaService kafkaService;

    @Override
    public void produceInventoryCreate(KafkaInventoryRequest message) {
        var topic = kafkaTopicProperties.getProductCreatedInventory();
        log.info("[produceInventoryCreate] producing error to topic {}", topic);
        kafkaService.send(topic, message);
    }

    @Override
    public void produceMessageError(KafkaObjectError kafkaObject) {
        var topic = kafkaTopicProperties.getErrorProduct();
        log.info("[produceMessageError] producing error to topic {}", topic);
        kafkaService.send(topic, kafkaObject);
    }
}

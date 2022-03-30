package com.example.commandapi.producer;

import com.example.commons.dto.command.ProductDTO;
import com.example.commons.event.ProductEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class ProductProducer {
    private final JmsTemplate jmsTemplate;

    @Value("${command.topic.product_create}")
    private String productCreateTopic;

    public void sendProductCreated(ProductDTO dto) {
        ProductEvent event = this.buildProductEvent().apply(dto);
        jmsTemplate.convertAndSend(productCreateTopic, event);
    }

    private Function<ProductDTO, ProductEvent> buildProductEvent() {
        return dto -> ProductEvent.builder()
                .id(dto.getId())
                .sku(dto.getSku())
                .name(dto.getName())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .createdAt(dto.getCreatedAt())
                .build();
    }
}

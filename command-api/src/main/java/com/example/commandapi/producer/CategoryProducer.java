package com.example.commandapi.producer;

import com.example.commons.dto.command.CategoryDTO;
import com.example.commons.event.CategoryEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class CategoryProducer {
    private final JmsTemplate jmsTemplate;

    @Value("${command.topic.category_create}")
    private String categoryCreateTopic;

    public void sendCategoryCreated(CategoryDTO dto) {
        CategoryEvent event = this.buildCategoryEvent().apply(dto);
        jmsTemplate.convertAndSend(categoryCreateTopic, event);
    }

    private Function<CategoryDTO, CategoryEvent> buildCategoryEvent() {
        return dto -> CategoryEvent.builder()
                .id(dto.getId())
                .name(dto.getName())
                .createdAt(dto.getCreatedAt())
                .build();
    }
}

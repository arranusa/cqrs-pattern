package com.example.queryapi.service;

import com.example.commons.event.CategoryEvent;
import com.example.queryapi.model.CategoryQuery;
import com.example.queryapi.repository.CategoryQueryRepository;
import com.example.queryapi.service.mapper.CategoryQueryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryQueryService {
    private final CategoryQueryRepository repository;

    @Transactional(transactionManager = "transactionManager")
    public void saveCategory(CategoryEvent event) {
        log.info("event {}", event);
        CategoryQuery query = CategoryQueryMapper.INSTANCE.toQuery(event);
        repository.save(query);
    }
}

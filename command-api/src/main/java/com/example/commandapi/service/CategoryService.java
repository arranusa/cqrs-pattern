package com.example.commandapi.service;

import com.example.commandapi.model.Category;
import com.example.commandapi.producer.CategoryProducer;
import com.example.commandapi.repository.CategoryRepository;
import com.example.commandapi.service.mapper.CategoryMapper;
import com.example.commons.dto.command.CategoryAddDTO;
import com.example.commons.dto.command.CategoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;
    private final CategoryProducer producer;

    @Transactional
    public CategoryDTO create(CategoryAddDTO dto) {
        Category entity = CategoryMapper.INSTANCE.toEntity(dto);
        CategoryDTO response =  CategoryMapper.INSTANCE.toDTO(repository.save(entity));
        producer.sendCategoryCreated(response);
        return response;
    }

    @Modifying
    @Transactional
    public CategoryDTO update(CategoryDTO dto) {
        CategoryDTO response =  repository.findById(dto.getId())
                .map(entity -> CategoryMapper.INSTANCE.toEntity(entity, dto))
                .map(CategoryMapper.INSTANCE::toDTO)
                .orElseThrow((() -> new EntityNotFoundException("Category with id " + dto.getId() + " not found")));

        producer.sendCategoryCreated(response);
        return response;
    }
}

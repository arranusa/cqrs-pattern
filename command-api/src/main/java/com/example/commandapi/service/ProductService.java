package com.example.commandapi.service;

import com.example.commandapi.model.Product;
import com.example.commandapi.producer.ProductProducer;
import com.example.commandapi.repository.ProductRepository;
import com.example.commandapi.service.mapper.ProductMapper;
import com.example.commons.dto.command.ProductAddDTO;
import com.example.commons.dto.command.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final ProductProducer producer;

    @Transactional
    public ProductDTO create(ProductAddDTO dto) {
        Product entity = ProductMapper.INSTANCE.toEntity(dto);
        ProductDTO response = ProductMapper.INSTANCE.toDTO(repository.save(entity));
        producer.sendProductCreated(response);
        return response;
    }

    @Modifying
    @Transactional
    public ProductDTO update(ProductDTO dto) {
        return repository.findById(dto.getId())
                .map(entity -> ProductMapper.INSTANCE.toEntity(entity, dto))
                .map(ProductMapper.INSTANCE::toDTO)
                .orElseThrow((() -> new EntityNotFoundException("Product with id " + dto.getId() + " not found")));
    }
}

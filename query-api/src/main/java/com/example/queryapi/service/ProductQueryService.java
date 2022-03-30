package com.example.queryapi.service;

import com.example.commons.dto.query.request.ProductListRequest;
import com.example.commons.dto.query.response.ProductListResponse;
import com.example.commons.event.ProductEvent;
import com.example.queryapi.model.ProductQuery;
import com.example.queryapi.repository.ProductQueryRepository;
import com.example.queryapi.service.mapper.ProductQueryMapper;
import com.example.queryapi.service.specification.ProductQuerySpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProductQueryService {
    private final ProductQueryRepository repository;

    @Transactional(readOnly = true)
    public ProductListResponse getList(ProductListRequest req) {
        Pageable pageable = PageRequest.of(req.getPage(),
                req.getSize(),
                Sort.by(Sort.Direction.fromString(req.getSortDirection().toString()), req.getSortBy()));

        ProductQuerySpecification specs = new ProductQuerySpecification(req);
        Page<ProductQuery> pages = repository.findAll(specs, pageable);

        return ProductListResponse.builder()
                .total(pages.getTotalElements())
                .page(pages.getNumber())
                .size(pages.getSize())
                .products(ProductQueryMapper.INSTANCE.toDTOList(pages.toList()))
                .build();
    }

    @Transactional(transactionManager = "transactionManager")
    public void saveProduct(ProductEvent event) {
        log.info("event {}", event);
        ProductQuery query = ProductQueryMapper.INSTANCE.toQuery(event);
        repository.save(query);
    }
}

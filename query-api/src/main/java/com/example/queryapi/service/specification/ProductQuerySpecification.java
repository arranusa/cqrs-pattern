package com.example.queryapi.service.specification;

import com.example.commons.dto.query.request.ProductListRequest;
import com.example.queryapi.model.ProductQuery;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ProductQuerySpecification implements Specification<ProductQuery> {
    private static final long serialVersionUID = 6728584335969222071L;

    private final ProductListRequest request;

    @Override
    public Predicate toPredicate(Root<ProductQuery> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.isNotEmpty(request.getSku())) {
            predicates.add(builder.or(
                    builder.like(builder.lower(root.get("sku")), "%" + request.getSku().toLowerCase() + "%"))
            );
        }

        if (StringUtils.isNotEmpty(request.getName())) {
            predicates.add(builder.or(
                    builder.like(builder.lower(root.get("name")), "%" + request.getName().toLowerCase() + "%"))
            );
        }

        Path<Double> price = root.get("price");
        if (request.getStartPrice() != null && request.getEndPrice() != null) {
            predicates.add(builder.between(price, request.getStartPrice(), request.getEndPrice()));
        } else {
            if (request.getStartPrice() != null)
                predicates.add(builder.greaterThanOrEqualTo(price, request.getStartPrice()));

            if (request.getEndPrice() != null)
                predicates.add(builder.lessThanOrEqualTo(price, request.getEndPrice()));
        }

        Path<Integer> stock = root.get("stock");
        if (request.getStartStock() != null && request.getEndStock() != null) {
            predicates.add(builder.between(stock, request.getStartStock(), request.getEndStock()));
        } else {
            if (request.getStartStock() != null)
                predicates.add(builder.greaterThanOrEqualTo(stock, request.getStartStock()));

            if (request.getEndStock() != null)
                predicates.add(builder.lessThanOrEqualTo(stock, request.getEndStock()));
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}

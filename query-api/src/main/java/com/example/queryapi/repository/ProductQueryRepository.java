package com.example.queryapi.repository;


import com.example.queryapi.model.ProductQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductQueryRepository extends JpaRepository<ProductQuery, String>, JpaSpecificationExecutor<ProductQuery> {

}

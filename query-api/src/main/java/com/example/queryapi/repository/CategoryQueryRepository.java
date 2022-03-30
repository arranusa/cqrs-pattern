package com.example.queryapi.repository;


import com.example.queryapi.model.CategoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryQueryRepository extends JpaRepository<CategoryQuery, String> {

}

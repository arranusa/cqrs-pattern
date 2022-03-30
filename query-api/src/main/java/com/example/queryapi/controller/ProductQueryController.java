package com.example.queryapi.controller;

import com.example.commons.dto.query.request.ProductListRequest;
import com.example.commons.dto.query.response.ProductListResponse;
import com.example.queryapi.service.ProductQueryService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductQueryController {
    private final ProductQueryService service;

    @GetMapping("/api/search")
    @ApiOperation(value = "list paging sorting filtering Product. Return array of Product")
    public ResponseEntity<ProductListResponse> getList(@Valid ProductListRequest request) {
        return ResponseEntity.ok(service.getList(request));
    }
}

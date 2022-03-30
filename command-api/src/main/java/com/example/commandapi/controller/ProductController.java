package com.example.commandapi.controller;

import com.example.commandapi.service.ProductService;
import com.example.commons.dto.command.ProductAddDTO;
import com.example.commons.dto.command.ProductDTO;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;


    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "create new Product")
    public ResponseEntity<ProductDTO> create(@RequestBody @Valid ProductAddDTO dto) {

        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "update data Product")
    public ResponseEntity<ProductDTO> update(@RequestBody @Valid ProductDTO dto) {
        return ResponseEntity.ok(service.update(dto));

    }
}

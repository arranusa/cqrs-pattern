package com.example.commandapi.controller;

import com.example.commandapi.service.CategoryService;
import com.example.commons.dto.command.CategoryAddDTO;
import com.example.commons.dto.command.CategoryDTO;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService service;


    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "create new Category")
    public ResponseEntity<CategoryDTO> create(@RequestBody @Valid CategoryAddDTO dto) {

        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "update data Category")
    public ResponseEntity<CategoryDTO> update(@RequestBody @Valid CategoryDTO dto) {
        return ResponseEntity.ok(service.update(dto));

    }
}

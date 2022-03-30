package com.example.commons.dto.query.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

@Value
@Builder
@AllArgsConstructor
public class ProductQueryDTO implements Serializable {
    private static final long serialVersionUID = 6429845957916473644L;
    String id;
    String sku;
    String name;
    Double price;
    Integer stock;
}

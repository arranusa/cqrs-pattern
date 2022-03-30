package com.example.commons.dto.query.response;

import com.example.commons.dto.query.payload.ProductQueryDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Builder
@AllArgsConstructor
@ToString
@Getter
public class ProductListResponse implements Serializable {
    private static final long serialVersionUID = -4078025417366872614L;

    private Long total;
    private Integer page;
    private Integer size;

    private List<ProductQueryDTO> products;
}

package com.example.commons.dto.query.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class ProductListRequest extends ListRequest {
    private static final long serialVersionUID = 8477162069589504398L;
    private String name;
    private String sku;
    private Double startPrice;
    private Double endPrice;
    private Integer startStock;
    private Integer endStock;
    private String categoryId;

}

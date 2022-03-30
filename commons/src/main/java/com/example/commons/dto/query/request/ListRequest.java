package com.example.commons.dto.query.request;

import com.example.commons.enums.SortDirection;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Data
public abstract class ListRequest implements Serializable {
    private static final long serialVersionUID = 7419949393484451914L;

    @Min(0)
    private Integer page = 0;

    @Min(1)
    @Max(50)
    private Integer size = 10;

    private SortDirection sortDirection = SortDirection.DESC;
    private String sortBy = "id";
}

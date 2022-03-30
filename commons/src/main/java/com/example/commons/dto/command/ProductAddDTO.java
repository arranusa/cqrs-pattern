package com.example.commons.dto.command;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
public class ProductAddDTO implements Serializable {
    private static final long serialVersionUID = 9109557222822920248L;

    @NotEmpty
    String sku;

    @NotEmpty
    @Length(max = 255)
    String name;

    @Min(value = 0)
    Double price;

    @Min(value = 0)
    Integer stock;
}

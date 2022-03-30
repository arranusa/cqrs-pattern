package com.example.commons.dto.command;

import com.example.commons.dto.AbstractBaseDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class ProductDTO extends AbstractBaseDTO {

    private static final long serialVersionUID = -3962864783727168204L;

    @NotEmpty
    String sku;

    @NotEmpty
    @Length(max = 255)
    String name;

    @NotEmpty
    @Min(value = 0)
    Double price;

    @Min(value = 0)
    @NotEmpty
    Integer stock;
}

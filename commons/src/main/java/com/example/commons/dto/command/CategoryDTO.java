package com.example.commons.dto.command;

import com.example.commons.dto.AbstractBaseDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString
public class CategoryDTO extends AbstractBaseDTO {
    private static final long serialVersionUID = -6986265469940580152L;

    @NotEmpty
    @Length(max = 255)
    String name;
}

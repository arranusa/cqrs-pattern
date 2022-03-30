package com.example.commandapi.service.mapper;

import com.example.commandapi.model.Product;
import com.example.commons.dto.command.ProductAddDTO;
import com.example.commons.dto.command.ProductDTO;
import com.example.commons.util.CommonHelper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(imports = CommonHelper.class)
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product toEntity(ProductAddDTO dto);

    Product toEntity(@MappingTarget Product entity, ProductDTO dto);

    ProductDTO toDTO(Product entity);


}

package com.example.queryapi.service.mapper;

import com.example.commons.dto.query.payload.ProductQueryDTO;
import com.example.commons.event.ProductEvent;
import com.example.queryapi.model.ProductQuery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductQueryMapper {
    ProductQueryMapper INSTANCE = Mappers.getMapper(ProductQueryMapper.class);

    ProductQueryDTO toDTO(ProductQuery entity);

    List<ProductQueryDTO> toDTOList(List<ProductQuery> entities);

    ProductQuery toQuery(ProductEvent event);
}

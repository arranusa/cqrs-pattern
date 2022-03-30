package com.example.queryapi.service.mapper;

import com.example.commons.event.CategoryEvent;
import com.example.queryapi.model.CategoryQuery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryQueryMapper {
    CategoryQueryMapper INSTANCE = Mappers.getMapper(CategoryQueryMapper.class);

    CategoryQuery toQuery(CategoryEvent event);
}

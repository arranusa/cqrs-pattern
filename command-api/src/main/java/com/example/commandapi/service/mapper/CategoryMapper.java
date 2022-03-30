package com.example.commandapi.service.mapper;

import com.example.commandapi.model.Category;
import com.example.commons.dto.command.CategoryAddDTO;
import com.example.commons.dto.command.CategoryDTO;
import com.example.commons.util.CommonHelper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(imports = CommonHelper.class)
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category toEntity(CategoryAddDTO dto);

    Category toEntity(@MappingTarget Category entity, CategoryDTO dto);

    CategoryDTO toDTO(Category entity);


}

package com.iphayao.demo.product;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "id", ignore = true)
    Product toEntity(ProductDto dto);

    @Mapping(target = "id", expression = "java(entity.getId().toString())")
    ProductDto toDto(Product entity);
}

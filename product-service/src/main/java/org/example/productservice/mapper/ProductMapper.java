package org.example.productservice.mapper;

import org.example.productservice.dto.ProductDTO;
import org.example.productservice.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface ProductMapper extends Converter<Product, ProductDTO> {

    @Override
    @Mapping(source = "category.name", target = "category.name")
    ProductDTO convert(Product product);

}

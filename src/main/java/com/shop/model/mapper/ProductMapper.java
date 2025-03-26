package com.shop.model.mapper;

import com.shop.model.dto.ProductDTO;
import com.shop.model.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toDTO(Product product);
    Product toEntity(ProductDTO productDTO);
    List<ProductDTO> toDTOList(List<Product> products);
    void updateEntity(@MappingTarget Product product, ProductDTO productDTO);
} 
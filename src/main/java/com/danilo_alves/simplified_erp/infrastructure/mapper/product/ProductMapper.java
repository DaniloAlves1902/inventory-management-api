package com.danilo_alves.simplified_erp.infrastructure.mapper.product;

import com.danilo_alves.simplified_erp.domain.entity.product.ProductDomain;
import com.danilo_alves.simplified_erp.infrastructure.persistence.entity.product.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "version", source = "version")
    ProductEntity toEntity(ProductDomain productDomain);

    ProductDomain toDomain(ProductEntity productEntity);

}
package com.ecommerce.ecommerce_application.mapper;

import org.springframework.stereotype.Component;

import com.ecommerce.ecommerce_application.domain.ProductD;
import com.ecommerce.ecommerce_application.dto.ProductRequest;
import com.ecommerce.ecommerce_application.dto.ProductResponse;
import com.ecommerce.ecommerce_application.entity.ProductE;

@Component
public class ProductMapper {

    public ProductE toEntity(ProductRequest request) {

        ProductE entity = new ProductE();

        entity.setProductName(request.productName().trim());
        entity.setDescription(request.description());
        entity.setPrice(request.price());
        entity.setStockQuantity(request.stockQuantity());

        return entity;
    }

    public ProductD toDomain(ProductE entity) {

        return new ProductD(
                entity.getProductId(),
                entity.getProductName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getStockQuantity()
        );
    }

    public ProductResponse toResponse(ProductD product) {

        return new ProductResponse(
                product.productId(),
                product.productName(),
                product.description(),
                product.price(),
                product.stockQuantity()
        );
    }
}
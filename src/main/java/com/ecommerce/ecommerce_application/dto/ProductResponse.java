package com.ecommerce.ecommerce_application.dto;



import java.math.BigDecimal;

public record ProductResponse(
        Long productId,
        String productName,
        String description,
        BigDecimal price,
        Integer stockQuantity
) {
}

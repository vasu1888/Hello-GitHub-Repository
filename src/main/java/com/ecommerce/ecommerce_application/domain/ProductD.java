package com.ecommerce.ecommerce_application.domain;

import java.math.BigDecimal;

public record ProductD(
		
		
        Long productId,
        String productName,
        String description,
        BigDecimal price,
        Integer stockQuantity) {
}
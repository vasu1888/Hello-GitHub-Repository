package com.ecommerce.ecommerce_application.dto;




public record CustomerResponse(
        Long customerId,
        String customerName,
        String email,
        String phone,
        String address
) {
}




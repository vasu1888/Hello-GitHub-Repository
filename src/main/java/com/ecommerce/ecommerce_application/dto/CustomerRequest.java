package com.ecommerce.ecommerce_application.dto;




import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CustomerRequest(

        @NotBlank(message = "Customer name is required")
        @Size(max = 100)
        String customerName,

        @NotBlank(message = "Email is required")
        @Email(message = "Enter a valid email")
        @Size(max = 150)
        String email,

        @Size(max = 15)
        String phone,

        @Size(max = 255)
        String address

) {
}



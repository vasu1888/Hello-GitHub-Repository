package com.ecommerce.ecommerce_application.mapper;

import org.springframework.stereotype.Component;

import com.ecommerce.ecommerce_application.dto.CustomerRequest;
import com.ecommerce.ecommerce_application.dto.CustomerResponse;
import com.ecommerce.ecommerce_application.entity.CustomerE;

@Component
public class CustomerMapper {

    public CustomerE toEntity(CustomerRequest request) {

        CustomerE customer = new CustomerE();

        customer.setCustomerName(request.customerName());
        customer.setEmail(request.email());
        customer.setPhone(request.phone());
        customer.setAddress(request.address());

        return customer;
    }

    public CustomerResponse toResponse(CustomerE customer) {

        return new CustomerResponse(
                customer.getCustomerId(),
                customer.getCustomerName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getAddress()
        );
    }
}
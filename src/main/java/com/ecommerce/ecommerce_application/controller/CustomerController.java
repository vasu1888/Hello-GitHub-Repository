package com.ecommerce.ecommerce_application.controller;



import java.net.URI;
import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.ecommerce_application.dto.CustomerRequest;
import com.ecommerce.ecommerce_application.dto.CustomerResponse;
import com.ecommerce.ecommerce_application.entity.CustomerE;
import com.ecommerce.ecommerce_application.mapper.CustomerMapper;
import com.ecommerce.ecommerce_application.service.CustomerService;


@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    public CustomerController(
            CustomerService customerService,
            CustomerMapper customerMapper) {

        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    // CREATE CUSTOMER
    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(
            @Valid @RequestBody CustomerRequest request) {

        CustomerE customer = customerService.createCustomer(request);

        CustomerResponse response =
                customerMapper.toResponse(customer);

        URI location = URI.create(
                "/api/customers/" + customer.getCustomerId());

        return ResponseEntity.created(location).body(response);
    }

    // GET CUSTOMER BY ID
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> getCustomerById(
            @PathVariable Long customerId) {

        CustomerE customer =
                customerService.getCustomerById(customerId);

        return ResponseEntity.ok(
                customerMapper.toResponse(customer));
    }

    // GET ALL CUSTOMERS
    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {

        List<CustomerResponse> responses =
                customerService.getAllCustomers()
                        .stream()
                        .map(customerMapper::toResponse)
                        .toList();

        return ResponseEntity.ok(responses);
    }

    // UPDATE CUSTOMER
    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> updateCustomer(
            @PathVariable Long customerId,
            @Valid @RequestBody CustomerRequest request) {

        CustomerE customer =
                customerService.updateCustomer(customerId, request);

        return ResponseEntity.ok(
                customerMapper.toResponse(customer));
    }

    // DELETE CUSTOMER
    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(
            @PathVariable Long customerId) {

        customerService.deleteCustomer(customerId);

        return ResponseEntity.noContent().build();
    }
}
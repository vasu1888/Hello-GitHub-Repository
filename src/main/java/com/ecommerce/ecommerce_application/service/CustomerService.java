package com.ecommerce.ecommerce_application.service;



import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.ecommerce_application.dto.CustomerRequest;
import com.ecommerce.ecommerce_application.entity.CustomerE;
import com.ecommerce.ecommerce_application.exception.CustomerNotFoundException;
import com.ecommerce.ecommerce_application.mapper.CustomerMapper;
import com.ecommerce.ecommerce_application.repository.CustomerRepository;

@Service
public class CustomerService implements ICustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(
            CustomerRepository customerRepository,
            CustomerMapper customerMapper) {

        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    @Transactional
    public CustomerE createCustomer(CustomerRequest request) {

        if (customerRepository.existsByEmailIgnoreCase(
                request.email())) {

            throw new IllegalArgumentException(
                    "Customer email already exists");
        }

        CustomerE customer = customerMapper.toEntity(request);

        return customerRepository.save(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerE getCustomerById(Long customerId) {

        return customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new CustomerNotFoundException(
                                "Customer not found: " + customerId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerE> getAllCustomers() {

        return customerRepository.findAll();
    }

    @Override
    @Transactional
    public CustomerE updateCustomer(
            Long customerId,
            CustomerRequest request) {

        CustomerE customer = getCustomerById(customerId);

        customerRepository.findAll().stream()
                .filter(existing ->
                        !existing.getCustomerId().equals(customerId)
                        && existing.getEmail().equalsIgnoreCase(
                                request.email()))
                .findFirst()
                .ifPresent(existing -> {
                    throw new IllegalArgumentException(
                            "Email already belongs to another customer");
                });

        customer.setCustomerName(request.customerName());
        customer.setEmail(request.email());
        customer.setPhone(request.phone());
        customer.setAddress(request.address());

        return customerRepository.save(customer);
    }

    @Override
    @Transactional
    public void deleteCustomer(Long customerId) {

        CustomerE customer = getCustomerById(customerId);

        customerRepository.delete(customer);
    }
}
package com.ecommerce.ecommerce_application.service;


import java.util.List;

import com.ecommerce.ecommerce_application.dto.CustomerRequest;
import com.ecommerce.ecommerce_application.entity.CustomerE;



public interface ICustomerService {

    CustomerE createCustomer(CustomerRequest request);

    CustomerE getCustomerById(Long customerId);

    List<CustomerE> getAllCustomers();

    CustomerE updateCustomer(
            Long customerId,
            CustomerRequest request);

    void deleteCustomer(Long customerId);
}

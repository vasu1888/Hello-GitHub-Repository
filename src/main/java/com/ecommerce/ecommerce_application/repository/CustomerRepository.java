package com.ecommerce.ecommerce_application.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.ecommerce_application.entity.CustomerE;

public interface CustomerRepository
        extends JpaRepository<CustomerE, Long> {

    boolean existsByEmailIgnoreCase(String email);
}
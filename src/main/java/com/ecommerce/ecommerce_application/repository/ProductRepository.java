package com.ecommerce.ecommerce_application.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.ecommerce_application.entity.ProductE;


public interface ProductRepository
        extends JpaRepository<ProductE, Long> {

    Optional<ProductE> findByProductNameIgnoreCase(String productName);

    boolean existsByProductNameIgnoreCase(String productName);
}
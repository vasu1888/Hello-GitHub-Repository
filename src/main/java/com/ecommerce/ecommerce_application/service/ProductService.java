package com.ecommerce.ecommerce_application.service;



import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.ecommerce_application.domain.ProductD;
import com.ecommerce.ecommerce_application.dto.ProductRequest;
import com.ecommerce.ecommerce_application.entity.ProductE;
import com.ecommerce.ecommerce_application.exception.DuplicateProductException;
import com.ecommerce.ecommerce_application.exception.InvalidProductException;
import com.ecommerce.ecommerce_application.exception.ProductNotFoundException;
import com.ecommerce.ecommerce_application.mapper.ProductMapper;
import com.ecommerce.ecommerce_application.repository.ProductRepository;


@Service
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(
            ProductRepository productRepository,
            ProductMapper productMapper) {

        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Transactional
    public ProductD createProduct(ProductRequest request) {

        if (request.price() == null
                || request.price().signum() <= 0) {
            throw new InvalidProductException(
                    "Price must be greater than zero"
            );
        }

        if (productRepository.existsByProductNameIgnoreCase(
                request.productName().trim())) {

            throw new DuplicateProductException(
                    "Product already exists: " + request.productName()
            );
        }

        ProductE entity = productMapper.toEntity(request);

        ProductE savedEntity = productRepository.save(entity);

        return productMapper.toDomain(savedEntity);
    }

    public List<ProductD> getAllProducts() {

        return productRepository.findAll()
                .stream()
                .map(productMapper::toDomain)
                .toList();
    }

    public ProductD getProductById(Long productId) {

        ProductE entity = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found with ID: " + productId
                        )
                );

        return productMapper.toDomain(entity);
    }

    @Transactional
    public ProductD updateProduct(
            Long productId,
            ProductRequest request) {

        ProductE entity = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found with ID: " + productId
                        )
                );

        if (request.price() == null
                || request.price().signum() <= 0) {
            throw new InvalidProductException(
                    "Price must be greater than zero"
            );
        }

        productRepository.findByProductNameIgnoreCase(
                request.productName().trim()
        ).filter(existing ->
                !existing.getProductId().equals(productId)
        ).ifPresent(existing -> {
            throw new DuplicateProductException(
                    "Another product already uses this name"
            );
        });

        entity.setProductName(request.productName().trim());
        entity.setDescription(request.description());
        entity.setPrice(request.price());
        entity.setStockQuantity(request.stockQuantity());

        ProductE updatedEntity = productRepository.save(entity);

        return productMapper.toDomain(updatedEntity);
    }

    @Transactional
    public void deleteProduct(Long productId) {

        ProductE entity = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found with ID: " + productId
                        )
                );

        productRepository.delete(entity);
    }
}





package com.ecommerce.ecommerce_application.controller;



import java.net.URI;
import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.ecommerce_application.domain.ProductD;
import com.ecommerce.ecommerce_application.dto.ProductRequest;
import com.ecommerce.ecommerce_application.dto.ProductResponse;
import com.ecommerce.ecommerce_application.mapper.ProductMapper;
import com.ecommerce.ecommerce_application.service.ProductService;


@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(
            ProductService productService,
            ProductMapper productMapper) {

        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(
            @Valid @RequestBody ProductRequest request) {

        ProductD product = productService.createProduct(request);

        ProductResponse response = productMapper.toResponse(product);

        URI location = URI.create("/api/products/" + product.productId());

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {

        List<ProductResponse> response = productService.getAllProducts()
                .stream()
                .map(productMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProductById(
            @PathVariable Long productId) {

    	ProductD product = productService.getProductById(productId);

        return ResponseEntity.ok(productMapper.toResponse(product));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long productId,
            @Valid @RequestBody ProductRequest request) {

    	ProductD product = productService.updateProduct(productId, request);

        return ResponseEntity.ok(productMapper.toResponse(product));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Long productId) {

        productService.deleteProduct(productId);

        return ResponseEntity.noContent().build();
    }
}
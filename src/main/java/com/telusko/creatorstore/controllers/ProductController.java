package com.telusko.creatorstore.controllers;

import com.telusko.creatorstore.entities.Product;
import com.telusko.creatorstore.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @PostMapping("/new")
    public Product addNewProduct(@Valid @RequestBody Product product) {
        return service.addNewProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProductById(@PathVariable Long id, @Valid @RequestBody Product product) {
        return service.updateProductById(id, product);
    }

    @GetMapping
    public List<Product> fetchAllProduct() {
        return service.fetchAllProduct();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return service.getProductById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long id) {
        service.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

}

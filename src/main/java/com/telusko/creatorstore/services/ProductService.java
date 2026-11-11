package com.telusko.creatorstore.services;

import com.telusko.creatorstore.entities.Product;
import com.telusko.creatorstore.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public Product addNewProduct(Product product) {
        return repository.save(product);
    }

    public Product updateProductById(Long id, Product product) {
        Product p = fetchById(id);

        p.setCategory(product.getCategory());
        p.setDescription(product.getDescription());
        p.setPrice(product.getPrice());
        p.setProductName(product.getProductName());
        p.setStockQuantity(product.getStockQuantity());

        return repository.save(p);

    }

    public List<Product> fetchAllProduct() {
        return repository.findAll();
    }

    public Product getProductById(Long id) {
        return fetchById(id);
    }

    public void deleteProductById(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Product with id: " + id + " not found");
        }
        repository.deleteById(id);
    }

    private Product fetchById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product with id: " + id + " not found"));
    }
}

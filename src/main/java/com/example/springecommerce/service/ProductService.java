package com.example.springecommerce.service;

import com.example.springecommerce.model.Product;

import java.util.Optional;

public interface ProductService {
    public Product save(Product product);
    public Optional<Product> get(Integer id);
    public void update(Product product);
    public void delete(Integer id);
}

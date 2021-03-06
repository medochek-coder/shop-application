package com.medochek.shopapp.service;

import com.medochek.shopapp.domain.Product;

import java.util.List;

public interface ProductService {
    Product createOrUpdate(Product product);
    Product getById(Long id);
    List<Product> getAll();
    List<Product> getAllSale();
    List<Product> getAllNoSale();
    void deleteById(Long id);
}

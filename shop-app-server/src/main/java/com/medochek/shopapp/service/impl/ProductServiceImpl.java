package com.medochek.shopapp.service.impl;

import com.medochek.shopapp.domain.Product;
import com.medochek.shopapp.repository.ProductRepository;
import com.medochek.shopapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository repository;

    @Override
    public Product createOrUpdate(Product product) {
        return repository.save(product);
    }

    @Override
    public Product getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Product> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Product> getAllSale() {
        return repository.findByPriceSaleIsNotNull();
    }

    @Override
    public List<Product> getAllNoSale() {
        return repository.findByPriceSaleIsNull();
    }

    @Override
    public void deleteById(Long id) {
        Product product = getById(id);
        if (product != null) {
            repository.deleteById(id);
        }
    }
}

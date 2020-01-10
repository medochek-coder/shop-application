package com.medochek.shopapp.service;

import com.medochek.shopapp.domain.Basket;

import java.util.List;

public interface BasketService {
    Basket createEmpty();
    void deleteById(Long id);
    void addProductById(Long idBasket, Long idProduct);
    void addProductById(Long idBasket, Long idProduct, Integer count);
    Integer changeCountProductById(Long idBasket, Long idProduct,  Integer countProduct);
    Integer incOrDecCountProductById(Long idBasket, Long idProduct, Boolean increase);
    void deleteProductById(Long idBasket, Long idProduct);
    void clearById(Long id);
    Basket getById(Long id);
    List<Basket> getAll();


}

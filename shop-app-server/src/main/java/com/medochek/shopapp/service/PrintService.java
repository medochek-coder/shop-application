package com.medochek.shopapp.service;

import java.util.List;

import com.medochek.shopapp.domain.Basket;
import com.medochek.shopapp.domain.Product;
import com.medochek.shopapp.domain.ProductOrder;

public interface PrintService {
    String printProductList(List<Product> products);
    String printBasket(Basket basket);
    String printBasketList(List<Basket> baskets);
    String printOrder(ProductOrder order);
    String printOrderList(List<ProductOrder> orders);
}

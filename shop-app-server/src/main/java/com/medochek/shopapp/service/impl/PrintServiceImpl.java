package com.medochek.shopapp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.medochek.shopapp.domain.Basket;
import com.medochek.shopapp.domain.BasketRow;
import com.medochek.shopapp.domain.Product;
import com.medochek.shopapp.domain.ProductOrder;
import com.medochek.shopapp.service.PrintService;

@Service
public class PrintServiceImpl implements PrintService {

    @Override
    public String printProductList(List<Product> products) {
        StringBuilder result = new StringBuilder();
        for (Product product : products) {
            result.append(product).append("\n");
        }
        return result.toString();
    }

    @Override
    public String printBasket(Basket basket) {
        StringBuilder result = new StringBuilder();
        result
                .append("Basket{\nid=")
                .append(basket.getId())
                .append("\n")
                .append("products=\n[");
        for (BasketRow basketRow : basket.getBasketRowList()) {
            result
                    .append(basketRow.getProduct())
                    .append(", count=")
                    .append(basketRow.getCount())
                    .append("\n");
        }
        result
                .append("]\n}");
        return result.toString();
    }

    @Override
    public String printBasketList(List<Basket> baskets) {
        StringBuilder result = new StringBuilder();
        for (Basket basket : baskets) {
            result
                    .append(printBasket(basket))
                    .append("\n");
        }
        return result.toString();
    }

    @Override
    public String printOrder(ProductOrder order) {
        StringBuilder result = new StringBuilder();
        result
                .append("Order{\nid=")
                .append(order.getId())
                .append(",{\n")
                .append("Basket{\nid=")
                .append(order.getBasket().getId())
                .append("\n")
                .append("products=\n[");
        for (BasketRow basketRow : order.getBasket().getBasketRowList()) {
            result
                    .append(basketRow.getProduct())
                    .append(", count=")
                    .append(basketRow.getCount())
                    .append("\n");
        }
        result
                .append("]\n}\n");
        return result.toString();
    }

    @Override
    public String printOrderList(List<ProductOrder> orders) {
        StringBuilder result = new StringBuilder();
        for (ProductOrder order : orders) {
            result
                    .append(printOrder(order))
                    .append("\n");
        }
        return result.toString();
    }
}

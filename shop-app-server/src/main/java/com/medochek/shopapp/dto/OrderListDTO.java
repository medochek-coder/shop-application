package com.medochek.shopapp.dto;

import com.medochek.shopapp.domain.Product;
import com.medochek.shopapp.domain.ProductOrder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class OrderListDTO {
    private List<OrderDTO> orders;

    public OrderListDTO(List<ProductOrder> orders) {
        this.orders = orders.stream()
                .map(order -> {
                    OrderDTO orderDTO = new OrderDTO(order);
                    return orderDTO;
                }).collect(Collectors.toList());
    }
}

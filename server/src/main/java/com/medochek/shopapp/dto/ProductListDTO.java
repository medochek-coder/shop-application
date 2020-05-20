package com.medochek.shopapp.dto;

import com.medochek.shopapp.domain.Product;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ProductListDTO {
    private List<ProductDTO> products;

    public ProductListDTO(List<Product> products) {
        this.products = products.stream()
                .map(product -> {
                    ProductDTO productDTO = new ProductDTO(product);
                    return productDTO;
        }).collect(Collectors.toList());
    }
}

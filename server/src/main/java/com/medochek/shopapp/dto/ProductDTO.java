package com.medochek.shopapp.dto;

import com.medochek.shopapp.domain.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private String image;
    private Double price;
    private Double priceSale;

    public ProductDTO(Product product) {
        if (product == null) {
            return;
        }
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.image = product.getImage();
        this.price = product.getPrice();
        this.priceSale = product.getPriceSale();
    }
}

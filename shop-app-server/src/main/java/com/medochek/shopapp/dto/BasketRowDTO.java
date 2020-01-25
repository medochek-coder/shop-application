package com.medochek.shopapp.dto;

import com.medochek.shopapp.domain.Basket;
import com.medochek.shopapp.domain.BasketRow;
import com.medochek.shopapp.domain.Product;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class BasketRowDTO {
    private Long id;
    private Integer count;
    private ProductDTO product;

    public BasketRowDTO(BasketRow basketRow) {
        this.id = basketRow.getId();
        this.count = basketRow.getCount();
        this.product = new ProductDTO(basketRow.getProduct());
    }
}

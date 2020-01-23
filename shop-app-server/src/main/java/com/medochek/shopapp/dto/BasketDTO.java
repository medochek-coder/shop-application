package com.medochek.shopapp.dto;

import com.medochek.shopapp.domain.Basket;
import com.medochek.shopapp.domain.BasketRow;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class BasketDTO {
    private Long id;
    private BasketRowListDTO basketRowList;

    public BasketDTO(Basket basket) {
        this.id = basket.getId();
        this.basketRowList = new BasketRowListDTO(basket.getBasketRowList());
    }
}

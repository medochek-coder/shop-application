package com.medochek.shopapp.dto;

import com.medochek.shopapp.domain.BasketRow;
import com.medochek.shopapp.domain.Product;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class BasketRowListDTO {
    private List<BasketRowDTO> basketRows;

    public BasketRowListDTO(List<BasketRow> basketRows) {
        if (basketRows == null) {
            this.basketRows = new ArrayList<>();
        } else {
            this.basketRows = basketRows.stream()
                    .map(basketRow -> {
                        BasketRowDTO basketRowDTO = new BasketRowDTO();
                        basketRowDTO.setId(basketRow.getId());
                        basketRowDTO.setCount(basketRow.getCount());
                        basketRowDTO.setProduct(new ProductDTO(basketRow.getProduct()));
                        return basketRowDTO;
                    }).collect(Collectors.toList());
        }

    }
}

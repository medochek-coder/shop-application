package com.medochek.shopapp.dto;

import com.medochek.shopapp.domain.Basket;
import com.medochek.shopapp.domain.ProductOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@NoArgsConstructor
@Data
public class OrderDTO {
    private Long id;
    private String ownerFirstName;
    private String ownerLastName;
    private String ownerPhone;
    private String ownerEmail;
    private String status;
    private LocalDateTime date;
    private BasketDTO basket;

    public OrderDTO(ProductOrder order) {
        this.id = order.getId();
        this.ownerFirstName = order.getOwnerFirstName();
        this.ownerLastName = order.getOwnerLastName();
        this.ownerPhone = order.getOwnerPhone();
        this.ownerEmail = order.getOwnerEmail();
        this.status = order.getStatus();
        this.date = order.getDate();
        this.basket = new BasketDTO(order.getBasket());
        setSalePrices(order.getPriceList());
    }

    private void setSalePrices(String priceList) {
        String[] products = priceList.split(";");
        if (products.length == 0) return;
        for (String product : products) {
            String[] price = product.split("-");
            if (price.length < 2) continue;
            ProductDTO productDTO = getProductDTOById(price[0]);
            if (productDTO != null) {
                productDTO.setPrice(Double.parseDouble(price[1]));
                productDTO.setPriceSale(null);
            }
        }
    }

    private ProductDTO getProductDTOById(String s) {
        Optional<BasketRowDTO> first = this.getBasket().getBasketRowList().getBasketRows().stream()
                .filter(basketRowDTO -> basketRowDTO.getProduct()
                .getId().equals(Long.parseLong(s))).findFirst();
        return first.map(BasketRowDTO::getProduct).orElse(null);
    }

}

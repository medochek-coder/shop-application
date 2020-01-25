package com.medochek.shopapp.dto;

import com.medochek.shopapp.domain.Basket;
import com.medochek.shopapp.domain.ProductOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    }
}

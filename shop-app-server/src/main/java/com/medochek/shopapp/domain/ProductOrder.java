package com.medochek.shopapp.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table
@Entity
@Data
@Builder
@AllArgsConstructor
@ToString(exclude = "basket")
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String ownerFirstName;
    private String ownerLastName;
    private String ownerPhone;
    private String ownerEmail;
    private String status;
    private LocalDateTime date;
    private String priceList;

    @OneToOne
    @JoinColumn(name = "basket_id")
    private Basket basket;

    public ProductOrder(Long id, String ownerFirstName, String ownerLastName, String ownerPhone, String ownerEmail,
                        String status, LocalDateTime date, Basket basket) {
        this.id = id;
        this.ownerFirstName = ownerFirstName;
        this.ownerLastName = ownerLastName;
        this.ownerPhone = ownerPhone;
        this.ownerEmail = ownerEmail;
        this.status = status;
        this.date = date;
        this.basket = basket;
        this.priceList = "";
        for (BasketRow basketRow : basket.getBasketRowList()) {
            this.priceList += basketRow.getProduct().getId() + "-";
            if(basketRow.getProduct().getPriceSale() != null) {
                this.priceList += basketRow.getProduct().getPriceSale() + ";";
            }
            else {
                this.priceList += basketRow.getProduct().getPrice() + ";";
            }
        }
    }
}

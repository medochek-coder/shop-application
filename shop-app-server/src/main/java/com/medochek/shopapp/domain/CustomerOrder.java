package com.medochek.shopapp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String ownerFirstName;

    private String ownerLastName;

    private String ownerPhone;

    private String ownerEmail;

    @OneToOne(fetch = FetchType.LAZY, targetEntity=Basket.class)
    @JoinColumn(name = "basket_id")
    private Basket basket;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", ownerFirstName='" + ownerFirstName + '\'' +
                ", ownerLastName='" + ownerLastName + '\'' +
                ", ownerPhone='" + ownerPhone + '\'' +
                ", ownerEmail='" + ownerEmail + '\'' +
                ", basket=" + basket +
                '}';
    }
}

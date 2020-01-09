package com.medochek.shopapp.domain;

import java.util.List;

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
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "basket")
    private CustomerOrder customerOrder;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "basket")
    private List<BasketRow> basketRows;

    @Override
    public String toString() {
        return "Basket{" +
                "id=" + id +
                ", basketRows=" + basketRows +
                '}';
    }
}

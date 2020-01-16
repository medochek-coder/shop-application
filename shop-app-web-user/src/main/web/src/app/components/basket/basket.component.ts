import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {BasketService} from "../../services/basket.service";
import {Basket} from "../../models/basket";


@Component({
    selector: 'basket',
    templateUrl: './basket.component.html',
    styleUrls: [
        './basket.component.css'
    ]
})

export class BasketComponent implements OnInit {

    public basket: Basket;

    constructor(private router: Router,
                private basketService: BasketService) {
    }

    ngOnInit() {
        this.initBasket();
    }

    private initBasket() {
        this.basket = this.basketService.getBasket();
    }

    gotoOrderPage() {
        this.router.navigate(['order']);
    }

    calculateTotalSum() {
        let totalSum = 0;
        this.basket.basketRows.forEach(basketRow => {
            totalSum += basketRow.product.price * basketRow.count;
        });
        return totalSum;
    }
}
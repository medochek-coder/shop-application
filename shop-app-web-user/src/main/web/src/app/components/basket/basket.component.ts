import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {BasketService} from "../../services/basket.service";
import {Basket} from "../../models/basket";
import {SharedService} from "../../services/shared.service";
import {Product} from "../../models/product";


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
                private shared: SharedService,
                private basketService: BasketService) {
    }

    ngOnInit() {
        this.basket = new Basket({});
        this.initBasket();
    }

    private initBasket() {
        let basketId = this.shared.getBasketIdFromStorage();
        this.basketService.getBasketById(basketId).subscribe(data => {
            this.basket = new Basket(data);
            console.log(this.basket);
        })
    }

    gotoOrderPage() {
        this.router.navigate(['order']);
    }

    calculateTotalSum() {
        let totalSum = 0;
        this.basket.basketRows.basketRowList.forEach(basketRow => {
            totalSum += basketRow.product.price * basketRow.count;
        });
        return totalSum;
    }
}
import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {BasketService} from "../../services/basket.service";
import {ProductService} from "../../services/product.service";
import {Product} from "../../models/product";
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
}
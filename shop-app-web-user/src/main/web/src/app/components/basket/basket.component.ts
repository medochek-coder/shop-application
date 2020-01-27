import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {BasketService} from "../../services/basket.service";
import {Basket} from "../../models/basket";
import {SharedService} from "../../services/shared.service";
import {BasketRow} from "../../models/basketRow";


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

    countMinus(basketRow: BasketRow, countNow: Number) {
        this.basketService.addProductById(this.basket.id, basketRow.product.id, -1).subscribe(data => {
            if (countNow === 0 || countNow.valueOf() - 1 === 0) {
                let index = this.basket.basketRows.basketRowList.findIndex(bRow => {
                    return bRow.id === basketRow.id;
                });
                if (index !== -1) this.basket.basketRows.basketRowList.splice(index, 1);
            } else {
                basketRow.count = countNow.valueOf() - 1;
            }
            this.shared.updateCurrentBasket();
        })
    }

    countPlus(basketRow: BasketRow, countNow: Number) {
        this.basketService.addProductById(this.basket.id, basketRow.product.id, 1).subscribe(data => {
            basketRow.count = countNow.valueOf() + 1;
            this.shared.updateCurrentBasket();
        })
    }

    countZero(basketRow: BasketRow) {
        this.basketService.deleteProductById(this.basket.id, basketRow.product.id).subscribe(data => {
            let index = this.basket.basketRows.basketRowList.findIndex(bRow => {
                return bRow.id === basketRow.id;
            });
            if (index !== -1) this.basket.basketRows.basketRowList.splice(index, 1);
            this.shared.updateCurrentBasket();
        });
    }
}
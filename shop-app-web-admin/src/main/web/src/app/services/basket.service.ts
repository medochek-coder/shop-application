import {Injectable} from '@angular/core';
import {Product} from "../models/product";
import {Basket} from "../models/basket";
import {ProductService} from "../services/product.service";
import {BasketRow} from "../models/basketRow";

@Injectable({
    providedIn: 'root'
})

export class BasketService {
    constructor(private productService: ProductService) {
    }

    public getBasket(): Basket {
        /*let allProducts = [];
        allProducts = this.productService.getProducts();
        let basket = new Basket(1);
        let basketRow1 = new BasketRow(1,3, basket, allProducts[2]);
        let basketRow2 = new BasketRow(2,1, basket, allProducts[5]);
        let basketRow3 = new BasketRow(3,33, basket, allProducts[1]);
        let basketRow4 = new BasketRow(4,6, basket, allProducts[7]);
        basket.basketRows.push(basketRow1);
        basket.basketRows.push(basketRow2);
        basket.basketRows.push(basketRow3);
        basket.basketRows.push(basketRow4);
        return basket;*/
        return null;
    }
}
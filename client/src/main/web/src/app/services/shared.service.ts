import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {Basket} from "../models/basket";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
    providedIn: 'root'
})
export class SharedService {

    private isProd: boolean = environment.production;

    private HOST_DEV = 'http://localhost';
    private PORT_DEV = '8080';

    private HOST_PROD = 'http://23.111.204.125:8080';

    private basket: Basket = new Basket({});
    private readonly SERVER_URL: string;

    private readonly GET_BASKET_BY_ID: string;

    constructor(private http: HttpClient) {
        this.SERVER_URL = this.getServerURL();
        this.GET_BASKET_BY_ID = this.SERVER_URL + '/api/basket/{id}';
    }

    public getServerURL() {
        return this.isProd ? (this.HOST_PROD) : (this.HOST_DEV + ':' + this.PORT_DEV);
    }

    public getBasketIdFromStorage(): Number {
        let item = localStorage.getItem("basketId");
        if (item !== null) {
            return Number(item);
        }
        return null;
    }

    public setBasketIdToStorage(basketId: Number) {
        localStorage.setItem("basketId", basketId.toString());
    }

    public getCurrentBasketProductCount() {
        let basketProductCount = 0;
        this.basket.basketRows.basketRowList.forEach(br => {
            basketProductCount +=br.count;
        });
        return basketProductCount;
    }

    public getCurrentBasketTotalPrice() {
        let basketTotalPrice = 0;
        this.basket.basketRows.basketRowList.forEach(br => {
            if(br.product.priceSale === null) {
                basketTotalPrice += br.count * br.product.price;
            }
            else {
                basketTotalPrice += br.count * br.product.priceSale;
            }
        });
        return basketTotalPrice;
    }

    public updateCurrentBasket() {
        if (this.getBasketIdFromStorage() !== null) {
            this.getBasketById(this.getBasketIdFromStorage()).subscribe(data => {
                this.basket = new Basket(data);
            })
        }
    }

    public getBasketById(basketId: Number) {
        const regExp = /{id}/gi;
        const url = this.GET_BASKET_BY_ID.replace(regExp, basketId.toString());
        return this.http.get<Observable<Object>>(url);
    }
}
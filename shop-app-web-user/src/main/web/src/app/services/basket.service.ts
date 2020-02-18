import {Injectable} from '@angular/core';
import {SharedService} from "./shared.service";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
    providedIn: 'root'
})

export class BasketService {

    private readonly SERVER_URL: string;

    private readonly GET_BASKET_BY_ID: string;
    private readonly CREATE_BASKET: string;
    private readonly ADD_PRODUCT_BY_ID: string;
    private readonly DELETE_BASKET_BY_ID: string;
    private readonly DELETE_PRODUCT_FROM_BASKET_BY_ID: string;


    constructor(private shared: SharedService,
                private http: HttpClient) {
        this.SERVER_URL = this.shared.getServerURL();
        this.CREATE_BASKET = this.SERVER_URL + '/api/basket/create';
        this.GET_BASKET_BY_ID = this.SERVER_URL + '/api/basket/{id}';
        this.ADD_PRODUCT_BY_ID = this.SERVER_URL + '/api/basket/add/{idB}/product/{idP}/count/{c}';
        this.DELETE_BASKET_BY_ID = this.SERVER_URL + '/api/basket/delete/{id}';
        this.DELETE_PRODUCT_FROM_BASKET_BY_ID = this.SERVER_URL + '/api/basket/delete/{idB}/product/{idP}';
    }

    public getBasketById(basketId: Number) {
        const regExp = /{id}/gi;
        const url = this.GET_BASKET_BY_ID.replace(regExp, basketId.toString());
        return this.http.get<Observable<Object>>(url);
    }

    public createBasket() {
        return this.http.post<Observable<Object>>(this.CREATE_BASKET, null);
    }

    public deleteBasketById(basketId: Number) {
        const regExp = /{id}/gi;
        const url = this.DELETE_BASKET_BY_ID.replace(regExp, basketId.toString());
        return this.http.delete<Observable<Object>>(url);
    }

    public addProductById(basketId: Number, productId: Number, count: Number) {
        const idB = /{idB}/gi;
        const idP = /{idP}/gi;
        const c = /{c}/gi;
        const url = this.ADD_PRODUCT_BY_ID.replace(idB, basketId.toString()).replace(idP, productId.toString())
            .replace(c, count.toString());
        return this.http.post<Observable<Object>>(url, null);
    }

    public deleteProductById(basketId: Number, productId: Number) {
        const idB = /{idB}/gi;
        const idP = /{idP}/gi;
        const url = this.DELETE_PRODUCT_FROM_BASKET_BY_ID.replace(idB, basketId.toString()).replace(idP, productId.toString());
        return this.http.delete<Observable<Object>>(url);
    }
}
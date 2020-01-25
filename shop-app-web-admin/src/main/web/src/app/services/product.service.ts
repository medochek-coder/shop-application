import {Injectable} from '@angular/core';
import {Product} from "../models/product";
import {SharedService} from "./shared.service";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class ProductService {
    private readonly SERVER_URL: string;

    private readonly GET_PRODUCT_BY_ID: string;
    private readonly GET_ALL_PRODUCTS: string;
    private readonly CREATE_PRODUCT: string;
    private readonly DELETE_PRODUCT_BY_ID: string;

    constructor(private shared: SharedService,
                private http: HttpClient) {
        this.SERVER_URL = this.shared.getServerURL();
        this.GET_PRODUCT_BY_ID = this.SERVER_URL + '/api/product/get/{id}';
        this.GET_ALL_PRODUCTS = this.SERVER_URL + '/api/product/get/all';
        this.CREATE_PRODUCT = this.SERVER_URL + '/api/product/create';
        this.DELETE_PRODUCT_BY_ID = this.SERVER_URL + '/api/product/delete/{id}';
    }

    public createProduct(product: Product) {
        return this.http.post<Observable<Object>>(this.CREATE_PRODUCT, product.toObject());
    }

    public getProductById(productId: Number) {
        const regExp = /{id}/gi;
        const url = this.GET_PRODUCT_BY_ID.replace(regExp, productId.toString());
        return this.http.get<Observable<Object>>(url);
    }

    public getProducts() {
        return this.http.get<Observable<Object>>(this.GET_ALL_PRODUCTS);
    }

    public deleteProductById(productId: Number) {
        const regExp = /{id}/gi;
        const url = this.DELETE_PRODUCT_BY_ID.replace(regExp, productId.toString());
        return this.http.delete<Observable<Object>>(url);
    }
}

import {Injectable} from '@angular/core';
import {ProductOrder} from "../models/productOrder";
import {BasketService} from "../services/basket.service";

@Injectable({
    providedIn: 'root'
})
export class ProductOrderService {
    constructor() {
    }

    public getOrder(): ProductOrder {
       return null;
    }
}
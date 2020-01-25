import {Injectable} from '@angular/core';
import {ProductOrder} from "../models/productOrder";
import {SharedService} from "./shared.service";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class ProductOrderService {

    private readonly SERVER_URL: string;

    private readonly GET_ORDER_BY_ID: string;
    private readonly COMPLETE_ORDER_BY_ID: string;
    private readonly GET_ALL_ORDERS: string;
    private readonly GET_IN_PROGRESS_ORDERS: string;
    private readonly CREATE_ORDER: string;
    private readonly GET_COMPLETED_ORDERS: string;


    constructor(private shared: SharedService,
                private http: HttpClient) {
        this.SERVER_URL = this.shared.getServerURL();

        this.GET_ORDER_BY_ID = this.SERVER_URL + '/api/order/get/{id}';
        this.COMPLETE_ORDER_BY_ID = this.SERVER_URL + '/api/order/complete/{id}';
        this.GET_ALL_ORDERS = this.SERVER_URL + '/api/order/get/all';
        this.GET_IN_PROGRESS_ORDERS = this.SERVER_URL + '/api/order/get/inprogress';
        this.GET_COMPLETED_ORDERS = this.SERVER_URL + '/api/order/get/completed';
        this.CREATE_ORDER = this.SERVER_URL + '/api/order/create';
    }

    public getOrders() {
        return this.http.get<Observable<Object>>(this.GET_ALL_ORDERS);
    }

    public getInProgressOrders() {
        return this.http.get<Observable<Object>>(this.GET_IN_PROGRESS_ORDERS);
    }

    public getCompletedOrders() {
        return this.http.get<Observable<Object>>(this.GET_COMPLETED_ORDERS);
    }

    public createOrder(order: ProductOrder) {
        return this.http.post<Observable<Object>>(this.CREATE_ORDER, order.toObject());
    }

    public getOrderById(orderId: Number) {
        const regExp = /{id}/gi;
        const url = this.GET_ORDER_BY_ID.replace(regExp, orderId.toString());
        return this.http.get<Observable<Object>>(url);
    }

    public completeOrderById(orderId: Number) {
        const regExp = /{id}/gi;
        const url = this.COMPLETE_ORDER_BY_ID.replace(regExp, orderId.toString());
        return this.http.put<Observable<Object>>(url, null);
    }

}
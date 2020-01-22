import { Component, OnInit } from '@angular/core';
import {ProductOrderService} from "../../services/productOrder.service";
import {Router} from "@angular/router";
import {ProductOrder} from "../../models/productOrder";

@Component({
    selector: 'orders',
    templateUrl: './orders.component.html',
    styleUrls: [
        './orders.component.css'
    ]
})

export class OrdersComponent implements OnInit {

    public orders: ProductOrder[] = [];

    constructor(private productOrderService: ProductOrderService,
                private router: Router) {
        this.initOrders();
    }

    ngOnInit() {
    }
    private initOrders() {
        for (let order of this.productOrderService.getOrders()) {
            if(order.status == "IN_PROGRESS") {
                this.orders.push(order);
            }
        }
    }

    public openOrderDetails(orderId: number) {
        this.router.navigate(['order/ +' + orderId]);
    }

    calculateTotalSum(order: ProductOrder) {
        let totalSum = 0;
        order.basket.basketRows.forEach(basketRow => {
            totalSum += basketRow.product.price * basketRow.count;
        });
        return totalSum;
    }

    isInProgress(order: ProductOrder) {
        if (order.status == "IN_PROGRESS") {
            return true;
        } else {
            return false;
        }
    }

}
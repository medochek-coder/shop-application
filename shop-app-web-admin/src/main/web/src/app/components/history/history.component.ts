import { Component, OnInit } from '@angular/core';
import {ProductOrder} from "../../models/productOrder";
import {ProductOrderService} from "../../services/productOrder.service";
import {Router} from "@angular/router";

@Component({
    selector: 'history',
    templateUrl: './history.component.html',
    styleUrls: [
        './history.component.css'
    ]
})

export class HistoryComponent implements OnInit {

    public orders: ProductOrder[] = [];

    constructor(private productOrderService: ProductOrderService,
                private router: Router) {
        this.initOrders();
    }

    ngOnInit() {
    }
    private initOrders() {
        this.orders = this.productOrderService.getOrders();

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
import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {ProductOrder} from "../../../models/productOrder";
import {ProductOrderService} from "../../../services/productOrder.service";
import {ProductOrderList} from "../../../models/productOrderList";

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
        this.productOrderService.getInProgressOrders().subscribe(data => {
            let orders = new ProductOrderList(data);
            this.orders = orders.productOrderList;
        });
    }

    public openOrderDetails(orderId: number) {
        this.router.navigate(['admin-order/ +' + orderId]);
    }

    public calculateTotalSum(order: ProductOrder) {
        let totalSum = 0;
        order.basket.basketRows.basketRowList.forEach(basketRow => {
            totalSum += basketRow.product.price * basketRow.count;
        });
        return totalSum;
    }

    public isInProgress(order: ProductOrder) {
        return order.status == "IN_PROGRESS";
    }
}
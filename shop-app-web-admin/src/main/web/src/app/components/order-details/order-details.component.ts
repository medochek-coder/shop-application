import { Component, OnInit } from '@angular/core';
import {ProductOrder} from "../../models/productOrder";
import {ActivatedRoute} from "@angular/router";
import {ProductOrderService} from "../../services/productOrder.service";
import {Basket} from "../../models/basket";

@Component({
    selector: 'order-details',
    templateUrl: './order-details.component.html',
    styleUrls: [
        './order-details.component.css'
    ]
})

export class OrderDetailsComponent implements OnInit {

    public orders: ProductOrder[] = [];
    public selectedOrder: ProductOrder;

    constructor(private route: ActivatedRoute,
                private productOrderService: ProductOrderService) {
    }

    ngOnInit() {
        let orderId = Number(this.route.snapshot.paramMap.get('id'));
        this.orders = this.productOrderService.getOrders();
        this.orders.forEach(order => {
            if (order.id == orderId) {
                this.selectedOrder = order;
            }
        });
    }
    calculateTotalSum() {
        let totalSum = 0;
        this.selectedOrder.basket.basketRows.forEach(basketRow => {
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
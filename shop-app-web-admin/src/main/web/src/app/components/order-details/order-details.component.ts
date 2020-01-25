import { Component, OnInit } from '@angular/core';
import {ProductOrder} from "../../models/productOrder";
import {ActivatedRoute} from "@angular/router";
import {ProductOrderService} from "../../services/productOrder.service";
import {Basket} from "../../models/basket";
import {Product} from "../../models/product";

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
        this.productOrderService.getOrderById(orderId).subscribe(data => {
            this.selectedOrder = new ProductOrder(data);
            console.log(this.selectedOrder);
        });
    }
    calculateTotalSum() {
        let totalSum = 0;
        this.selectedOrder.basket.basketRows.basketRowList.forEach(basketRow => {
            totalSum += basketRow.product.price * basketRow.count;
        });
        return totalSum;
    }

    isInProgress() {
        if (this.selectedOrder.status && this.selectedOrder.status == "IN_PROGRESS") {
            return true;
        } else {
            return false;
        }
    }

    complete() {
        this.productOrderService.completeOrderById(this.selectedOrder.id).subscribe(data => {
            this.selectedOrder = new ProductOrder(data);
            console.log(this.selectedOrder);
            this.ngOnInit();
        });
    }
}
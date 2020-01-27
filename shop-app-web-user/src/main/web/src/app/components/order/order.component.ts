import {Component, OnInit} from '@angular/core';
import {FormControl, Validators} from '@angular/forms';
import {Router} from "@angular/router";
import {SharedService} from "../../services/shared.service";
import {ProductOrderService} from "../../services/productOrder.service";
import {Basket} from "../../models/basket";
import {ProductOrder} from "../../models/productOrder";
import {BasketService} from "../../services/basket.service";
import {MatDialog} from "@angular/material/dialog";
import {ActionPopup} from "../pupup/action.popup";


@Component({
    selector: 'order',
    templateUrl: './order.component.html',
    styleUrls: [
        './order.component.css'
    ]
})

export class OrderComponent implements OnInit {

    public order: ProductOrder;
    public basket: Basket;

    constructor(private router: Router,
                private shared: SharedService,
                private productOrderService: ProductOrderService,
                private basketService: BasketService,
                private dialog: MatDialog) {
    }

    ngOnInit() {
        this.order = new ProductOrder({});
    }

    emailFormControl = new FormControl('', [
        Validators.required,
        Validators.email,
    ]);


    createOrder() {
        let basketId = this.shared.getBasketIdFromStorage();
        this.basketService.getBasketById(basketId).subscribe(data => {
            this.basket = new Basket(data);
            this.order.basket = this.basket;
            this.order.date = new Date();
            this.order.status = "IN_PROGRESS";
            this.productOrderService.createOrder(this.order).subscribe(date => {
                this.order = new ProductOrder(date);
                this.basketService.createBasket().subscribe( data => {
                    this.basket = new Basket(data);
                    this.shared.setBasketIdToStorage(this.basket.id);
                    this.openActionPopup();
                });
            });
        });
    }

    public openActionPopup() {
        const dialogRef = this.dialog.open(ActionPopup, {data : {title: 'Готово', info: 'Номер вашего заказа: ' + this.order.id}});
        dialogRef.afterClosed().subscribe(result => {
            this.router.navigate(['home']);
        });
    }

    isFilledFields() : boolean {
        return this.order.ownerFirstName.trim() !== '' && this.order.ownerLastName.trim() !== '' &&
            this.order.ownerPhone.trim() !== '' && this.order.ownerEmail.trim() !== '';
    }
}
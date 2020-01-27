import {Component, OnInit} from '@angular/core';
import {Product} from "../../models/product";
import {ActivatedRoute, Router} from "@angular/router";
import {ProductService} from "../../services/product.service";
import {SharedService} from "../../services/shared.service";
import {BasketService} from "../../services/basket.service";
import {ActionPopup} from "../pupup/action.popup";
import {MatDialog} from "@angular/material/dialog";


@Component({
    selector: 'product',
    templateUrl: './product.component.html',
    styleUrls: [
        './product.component.css'
    ]
})

export class ProductComponent implements OnInit {

    public products: Product[] = [];
    public selectedProduct: Product;
    public count: number = 1;

    constructor(private route: ActivatedRoute,
                private router: Router,
                private shared: SharedService,
                private productService: ProductService,
                private basketService: BasketService,
                private dialog: MatDialog) {
    }

    ngOnInit() {
        let productId = Number(this.route.snapshot.paramMap.get('id'));
        this.productService.getProductById(productId).subscribe(data => {
            this.selectedProduct = new Product(data);
        })
    }

    public addProductToBasket() {
        let basketId = this.shared.getBasketIdFromStorage();
        this.basketService.addProductById(basketId, this.selectedProduct.id, this.count).subscribe(data => {
            this.shared.updateCurrentBasket();
            this.openActionPopup();
        })
    }

    public countMinus() {
        if(this.count !== 0) {
            this.count--;
        }
    }

    public countPlus() {
        if(this.count !== 1000) {
            this.count++;
        }
    }

    public openActionPopup() {
        const dialogRef = this.dialog.open(ActionPopup, {data : {title: 'Готово', info: 'Продукт добавлен в корзину', isProduct: true}});
        dialogRef.afterClosed().subscribe(result => {
            if (result !== null) {
                this.router.navigate(['basket']);
            } else {
                this.router.navigate(['home']);
            }
        });
    }
}

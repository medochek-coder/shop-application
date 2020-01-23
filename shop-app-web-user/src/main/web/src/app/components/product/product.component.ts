import { Component, OnInit } from '@angular/core';
import {Product} from "../../models/product";
import {ActivatedRoute} from "@angular/router";
import {ProductService} from "../../services/product.service";
import {ProductList} from "../../models/productList";
import {SharedService} from "../../services/shared.service";
import {BasketService} from "../../services/basket.service";


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
    public count: Number = 1;

    constructor(private route: ActivatedRoute,
                private shared: SharedService,
                private productService: ProductService,
                private basketService: BasketService) {
    }

    ngOnInit() {
        let productId = Number(this.route.snapshot.paramMap.get('id'));
        this.productService.getProductById(productId).subscribe(data => {
            this.selectedProduct = new Product(data);
        })
    }

    addProductToBasket() {
        let basketId = this.shared.getBasketIdFromStorage();
        this.basketService.addProductById(basketId, this.selectedProduct.id, this.count).subscribe(data => {
            this.ngOnInit();
        })

    }
}

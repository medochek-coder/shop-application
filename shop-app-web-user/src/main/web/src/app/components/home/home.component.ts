import { Component, OnInit } from '@angular/core';
import {Product} from "../../models/product";
import {ProductService} from "../../services/product.service";
import {Router} from "@angular/router";


@Component({
    selector: 'home',
    templateUrl: './home.component.html',
    styleUrls: [
        './home.component.css'
    ]
})

export class HomeComponent implements OnInit {

    public products: Product[] = [];

    constructor(private productService: ProductService,
                private router: Router) {
    }

    ngOnInit() {
        this.initProducts();
    }

    private initProducts() {
        this.products = this.productService.getProducts();
    }

    public openProductDetails(productId: number) {
        this.router.navigate(['product/ +' + productId]);
    }
}

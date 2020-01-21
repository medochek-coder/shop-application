import { Component, OnInit } from '@angular/core';
import {Product} from "../../models/product";
import {ActivatedRoute} from "@angular/router";
import {ProductService} from "../../services/product.service";

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
    public isCreate: boolean;

    constructor(private route: ActivatedRoute,
                private productService: ProductService) {
    }

    ngOnInit() {
        if (this.route.snapshot.paramMap.get('id').includes('new')) {
            this.isCreate = true;
        } else {
            this.isCreate = false;
        }
        if (!this.isCreate) {
            let productId = Number(this.route.snapshot.paramMap.get('id'));
            this.products = this.productService.getProducts();
            this.products.forEach(product => {
                if (product.id == productId) {
                    this.selectedProduct = product;
                }
            });
        } else {
            this.selectedProduct = new Product(null, "", null, "", "");
        }
    }
}
import { Component, OnInit } from '@angular/core';
import {Product} from "../../models/product";
import {ActivatedRoute, Router} from "@angular/router";
import {ProductService} from "../../services/product.service";

@Component({
    selector: 'product',
    templateUrl: './product.component.html',
    styleUrls: [
        './product.component.css'
    ]
})

export class ProductComponent implements OnInit {
    public selectedProduct: Product;
    public isCreate: boolean;

    constructor(private route: ActivatedRoute,
                private routing: Router,
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
            this.productService.getProductById(productId).subscribe(data => {
                this.selectedProduct = new Product(data);
            });

        } else {
            this.selectedProduct = new Product({
                id: null,
                name: '',
                description: '',
                image: '',
                price: '',
                priceSale: null
            });
        }
    }

    createProduct() {
        this.productService.createProduct(this.selectedProduct).subscribe(date => {
            this.routing.navigate(['home']);
        });
    }
}
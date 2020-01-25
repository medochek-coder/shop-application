import {Component, OnInit} from '@angular/core';
import {Product} from "../../models/product";
import {ProductService} from "../../services/product.service";
import {Router} from "@angular/router";
import {ProductList} from "../../models/productList";

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
        this.productService.getProducts().subscribe(data => {
            let products = new ProductList(data);
            this.products = products.productList;
        });
    }

    public updateProduct(productId: number) {
        this.router.navigate(['product/ +' + productId]);
    }

    public deleteProduct(productId: number) {
        this.productService.deleteProductById(productId).subscribe(date => {
            this.router.navigate(['home']);
            this.initProducts();
        });
    }
}

import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {Product} from "../../../models/product";
import {ProductService} from "../../../services/product.service";
import {ProductList} from "../../../models/productList";

@Component({
    selector: 'ahome',
    templateUrl: './ahome.component.html',
    styleUrls: [
        './ahome.component.css'
    ]
})

export class AhomeComponent implements OnInit {

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
        this.router.navigate(['admin-product/ +' + productId]);
    }

    public deleteProduct(productId: number) {
        this.productService.deleteProductById(productId).subscribe(date => {
            this.router.navigate(['admin-home']);
            this.initProducts();
        });
    }

    public isSale( product: Product) {
        return product.priceSale !== null;
    }
}

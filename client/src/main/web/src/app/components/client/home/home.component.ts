import {Component, OnInit} from '@angular/core';
import {Product} from "../../../models/product";
import {ProductService} from "../../../services/product.service";
import {Router} from "@angular/router";
import {ProductList} from "../../../models/productList";
import {SharedService} from "../../../services/shared.service";
import {BasketService} from "../../../services/basket.service";
import {Basket} from "../../../models/basket";


@Component({
    selector: 'home',
    templateUrl: './home.component.html',
    styleUrls: [
        './home.component.css'
    ]
})

export class HomeComponent implements OnInit {

    public products: Product[] = [];
    public basket: Basket;

    constructor(private productService: ProductService,
                private router: Router,
                private shared: SharedService,
                private basketService: BasketService) {
    }

    ngOnInit() {
        this.initBasket();
        this.initProducts();
    }

    private initProducts() {
        this.productService.getProducts().subscribe(data => {
            let products = new ProductList(data);
            this.products = products.productList;
        });
    }

    public openProductDetails(productId: number) {
        this.router.navigate(['product/ +' + productId]);
    }

    private initBasket() {
        if (this.shared.getBasketIdFromStorage() === null) {
            this.basketService.createBasket().subscribe( data => {
                this.basket = new Basket(data);
                this.shared.setBasketIdToStorage(this.basket.id);
            })
        }
    }

    public isSale( product: Product) {
        return product.priceSale !== null;
    }
}

import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {Basket} from "./models/basket";
import {SharedService} from "./services/shared.service";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: [
        './app.component.css'
    ]
})

export class AppComponent {

    constructor(private router: Router,
                private shared: SharedService) {
    }

    public gotoBasketPage() {
        this.router.navigate(['basket']);
    }

    public gotoHomePage() {
        this.router.navigate(['home']);
    }

    public getProductCount() {
        return this.shared.getCurrentBasketProductCount();
    }

    public getTotalPrice() {
        return this.shared.getCurrentBasketTotalPrice();
    }
}

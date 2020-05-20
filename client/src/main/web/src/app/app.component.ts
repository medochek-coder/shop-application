import {Component} from '@angular/core';
import {Router} from "@angular/router";
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

    public gotoAHomePage() {
        this.router.navigate(['admin-home']);
    }

    public gotoNewProductPage() {
        this.router.navigateByUrl('/RefreshComponent', { skipLocationChange: true }).then(() => {
            this.router.navigate(['admin-product/new']);
        });
    }

    public getProductCount() {
        return this.shared.getCurrentBasketProductCount();
    }

    public getTotalPrice() {
        return this.shared.getCurrentBasketTotalPrice();
    }

    public isAdmin() {
        return this.router.url.includes('/admin');
    }
}

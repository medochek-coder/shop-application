﻿import { Component } from '@angular/core';
import {Router} from "@angular/router";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: [
        './app.component.css'
    ]
})

export class AppComponent {
    constructor(private router: Router) {
    }

    gotoHomePage() {

    }

    public isMobile = () => screen.width < 481;

    gotoNewProductPage() {
        this.router.navigateByUrl('/RefreshComponent', { skipLocationChange: true }).then(() => {
            this.router.navigate(['product/new']);
        });
    }
}

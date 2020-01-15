import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";


@Component({
    selector: 'basket',
    templateUrl: './basket.component.html',
    styleUrls: [
        './basket.component.css'
    ]
})

export class BasketComponent implements OnInit {

    constructor(private router: Router) {
    }

    ngOnInit() {
    }

    gotoOrderPage() {
        this.router.navigate(['order']);
    }
}
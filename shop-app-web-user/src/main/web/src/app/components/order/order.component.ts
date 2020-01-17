import { Component, OnInit } from '@angular/core';
import {FormControl, Validators} from '@angular/forms';

@Component({
    selector: 'order',
    templateUrl: './order.component.html',
    styleUrls: [
        './order.component.css'
    ]
})

export class OrderComponent implements OnInit {

    constructor() {
    }

    ngOnInit() {
    }
    emailFormControl = new FormControl('', [
        Validators.required,
        Validators.email,
    ]);
}
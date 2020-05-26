import {Component, Inject, OnDestroy} from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import {Router} from "@angular/router";

@Component({
    selector: 'action.popup',
    templateUrl: 'action.popup.html',
    styleUrls: ['action.popup.css']
})
export class ActionPopup {
    public title = '';
    public info = '';
    public isProduct = false;
    public isGoBasket = {isGoBasket: true};

    constructor(
        public dialogRef: MatDialogRef<ActionPopup>,
        private router: Router,
        @Inject(MAT_DIALOG_DATA) public data: any) {
        this.title = data.title;
        this.info = data.info;
        this.isProduct = data.isProduct;
    }

    private closePopup() {
        this.dialogRef.close();
    }

    public isAdmin() {
        return this.router.url.includes('/admin');
    }
}
import {Component, Inject, OnDestroy} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';

@Component({
    selector: 'action.popup',
    templateUrl: 'action.popup.html',
    styleUrls: ['action.popup.css']
})
export class ActionPopup {
    private title = '';
    private info = '';
    private isProduct = false;
    private isGoBasket = {isGoBasket: true};

    constructor(
        public dialogRef: MatDialogRef<ActionPopup>,
        @Inject(MAT_DIALOG_DATA) public data: any) {
        this.title = data.title;
        this.info = data.info;
        this.isProduct = data.isProduct;
    }

    private closePopup() {
        this.dialogRef.close();
    }
}
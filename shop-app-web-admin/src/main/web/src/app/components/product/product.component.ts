import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'product',
    templateUrl: './product.component.html',
    styleUrls: [
        './product.component.css'
    ]
})

export class ProductComponent implements OnInit {

    constructor() {
        // как параметр пути мы передаем или id товара который будем редактировать,
        // или слово new при создании нового товара, в зависимости от этого страничка
        // будет выглядеть немного по-разному, т.е позволять нам отредактировать текущий товар
        // или создать новый, по сути, текствоые поля для создания товара будут просто
        // заполняться данными имеющегося товара, если мы редактируем и будут пустые если создаем
        // новый. Вот и все различия
    }

    ngOnInit() {
    }
}
import {Product} from "./product";

export class ProductList {
    private _productList: Product[] = [];


    constructor(data: any) {
        data.products.forEach(product => {
            this._productList.push(new Product(product));
        });
    }

    get productList(): Product[] {
        return this._productList;
    }

    set productList(value: Product[]) {
        this._productList = value;
    }
}
import {ProductOrder} from "./productOrder";

export class ProductOrderList {
    private _productOrderList: ProductOrder[] = [];

    constructor(data: any) {
        data.orders.forEach(order => {
            this._productOrderList.push(new ProductOrder(order));
        });
    }

    get productOrderList(): ProductOrder[] {
        return this._productOrderList;
    }

    set productOrderList(value: ProductOrder[]) {
        this._productOrderList = value;
    }
}
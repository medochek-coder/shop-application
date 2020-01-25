import {Product} from "./product";

export class BasketRow {
    private _id: number;
    private _count: number;
    private _product:Product;

    constructor(data: any) {
        this._id = data.id;
        this._count = data.count;
        this._product = new Product(data.product);
    }

    get id(): number {
        return this._id;
    }

    set id(value: number) {
        this._id = value;
    }

    get count(): number {
        return this._count;
    }

    set count(value: number) {
        this._count = value;
    }

    get product(): Product {
        return this._product;
    }

    set product(value: Product) {
        this._product = value;
    }

    public toObject() {
        return {
            id: this._id,
            count: this._count,
            product: this._product.toObject()
        }
    }
}
import {Basket} from "./basket";
import {Product} from "./product";

export class BasketRow {
    private _id: number;
    private _count: number;
    private _basket: Basket;
    private _product:Product;

    constructor(id: number, count: number, basket: Basket, product: Product) {
        this._id = id;
        this._count = count;
        this._basket = basket;
        this._product = product;
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

    get basket(): Basket {
        return this._basket;
    }

    set basket(value: Basket) {
        this._basket = value;
    }

    get product(): Product {
        return this._product;
    }

    set product(value: Product) {
        this._product = value;
    }
}
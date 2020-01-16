import {BasketRow} from "./basketRow";

export class Basket {
    private _id: number;
    private _basketRows: BasketRow[];

    constructor(id: number) {
        this._id = id;
        this.basketRows = [];
    }

    get id(): number {
        return this._id;
    }

    set id(value: number) {
        this._id = value;
    }

    get basketRows(): BasketRow[] {
        return this._basketRows;
    }

    set basketRows(value: BasketRow[]) {
        this._basketRows = value;
    }
}
import {BasketRow} from "./basketRow";
import {BasketRowList} from "./basketRowList";

export class Basket {
    private _id: number;
    private _basketRows: BasketRowList;

    constructor(data: any) {
        this._id = data.id ? data.id : null;
        this._basketRows = data.basketRowList  ? new BasketRowList(data.basketRowList) : new BasketRowList({});
    }

    get id(): number {
        return this._id;
    }

    set id(value: number) {
        this._id = value;
    }


    get basketRows(): BasketRowList {
        return this._basketRows;
    }

    set basketRows(value: BasketRowList) {
        this._basketRows = value;
    }
}
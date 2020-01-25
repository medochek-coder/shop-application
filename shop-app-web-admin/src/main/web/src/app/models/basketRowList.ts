import {BasketRow} from "./basketRow";


export class BasketRowList {
    private _basketRowList: BasketRow[] = [];

    constructor(data: any) {
        if (data.basketRows) {
            data.basketRows.forEach(basketRow => {
                this._basketRowList.push(new BasketRow(basketRow));
            });
        }
    }

    get basketRowList(): BasketRow[] {
        return this._basketRowList;
    }

    set basketRowList(value: BasketRow[]) {
        this._basketRowList = value;
    }

    public toObject() {
        let basketArr = [];
        this._basketRowList.forEach(basketRow => {
            basketArr.push(basketRow.toObject())
        });
        return {
            basketRows: basketArr
        }
    }
}
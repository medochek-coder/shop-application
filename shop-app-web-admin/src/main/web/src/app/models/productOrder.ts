import {Basket} from "./basket";

export class ProductOrder {
    private _id: number;
    private _ownerFirstName: string;
    private _ownerLastName: string;
    private _ownerPhone: string;
    private _ownerEmail: string;
    private _status: string;
    private _date: Date;
    private _basket: Basket;

    constructor(data: any) {
        this._id = data.id ? data.id : null;
        this._ownerFirstName = data.ownerFirstName ? data.ownerFirstName : "";
        this._ownerLastName = data.ownerLastName ? data.ownerLastName : "";
        this._ownerPhone = data.ownerPhone ? data.ownerPhone : "";
        this._ownerEmail = data.ownerEmail ? data.ownerEmail : "";
        this._status = data.status ? data.status : "IN_PROGRESS";
        this._date = data.date ? data.date : null;
        if (data.basket) {
            this._basket = new Basket(data.basket)
        } else {
            this._basket = new Basket({});
        }

    }

    get id(): number {
        return this._id;
    }

    set id(value: number) {
        this._id = value;
    }

    get ownerFirstName(): string {
        return this._ownerFirstName;
    }

    set ownerFirstName(value: string) {
        this._ownerFirstName = value;
    }

    get ownerLastName(): string {
        return this._ownerLastName;
    }

    set ownerLastName(value: string) {
        this._ownerLastName = value;
    }

    get ownerPhone(): string {
        return this._ownerPhone;
    }

    set ownerPhone(value: string) {
        this._ownerPhone = value;
    }

    get ownerEmail(): string {
        return this._ownerEmail;
    }

    set ownerEmail(value: string) {
        this._ownerEmail = value;
    }

    get status(): string {
        return this._status;
    }

    set status(value: string) {
        this._status = value;
    }

    get date(): Date {
        return this._date;
    }

    set date(value: Date) {
        this._date = value;
    }

    get basket(): Basket {
        return this._basket;
    }

    set basket(value: Basket) {
        this._basket = value;
    }

    public toObject() {
        return {
            id: this._id,
            ownerFirstName: this._ownerFirstName,
            ownerLastName: this._ownerLastName,
            ownerPhone: this._ownerPhone,
            ownerEmail: this._ownerEmail,
            status: this._status,
            date: this._date,
            basket: this._basket.toObject()
        }
    }
}
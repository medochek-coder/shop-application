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


    constructor(id: number, ownerFirstName: string, ownerLastName: string, ownerPhone: string, ownerEmail: string, status: string, date: Date, basket: Basket) {
        this._id = id;
        this._ownerFirstName = ownerFirstName;
        this._ownerLastName = ownerLastName;
        this._ownerPhone = ownerPhone;
        this._ownerEmail = ownerEmail;
        this._status = status;
        this._date = date;
        this._basket = basket;
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
}
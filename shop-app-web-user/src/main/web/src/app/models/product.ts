export class Product {
    private _id: number;
    private _name: string;
    private _price: number;
    private _description: string;
    private _photo: string;


    constructor(data: any) {
        this._id = data.id;
        this._name = data.name;
        this._price = data.price;
        this._description = data.description;
        this._photo = data.image;
    }

    get id(): number {
        return this._id;
    }

    set id(value: number) {
        this._id = value;
    }

    get name(): string {
        return this._name;
    }

    set name(value: string) {
        this._name = value;
    }

    get price(): number {
        return this._price;
    }

    set price(value: number) {
        this._price = value;
    }

    get description(): string {
        return this._description;
    }

    set description(value: string) {
        this._description = value;
    }

    get photo(): string {
        return this._photo;
    }

    set photo(value: string) {
        this._photo = value;
    }
}
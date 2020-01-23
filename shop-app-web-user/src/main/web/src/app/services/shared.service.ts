import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';

@Injectable({
    providedIn: 'root'
})
export class SharedService {

    private isProd: boolean = environment.production;

    private HOST_DEV = 'http://localhost';
    private PORT_DEV = '8080';

    private HOST_PROD = 'https://web-shop-user.herokuapp.com';

    constructor() {}


    public getServerURL() {
        return this.isProd ? (this.HOST_PROD) : (this.HOST_DEV + ':' + this.PORT_DEV);
    }

    public getBasketIdFromStorage(): Number {
        let item = localStorage.getItem("basketId");
        if (item !== null) {
            return Number(item);
        }
        return null;
    }

    public setBasketIdToStorage(basketId: Number) {
        localStorage.setItem("basketId", basketId.toString());
    }
}
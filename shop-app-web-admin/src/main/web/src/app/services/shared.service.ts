import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';

@Injectable({
    providedIn: 'root'
})
export class SharedService {

    private isProd: boolean = environment.production;

    private HOST_DEV = 'http://localhost';
    private PORT_DEV = '8080';

    private HOST_PROD = 'https://web-shop-admintool.herokuapp.com';

    constructor() {}


    public getServerURL() {
        return this.isProd ? (this.HOST_PROD) : (this.HOST_DEV + ':' + this.PORT_DEV);
    }
}
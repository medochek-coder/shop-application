import {Injectable} from '@angular/core';
import {ProductOrder} from "../models/productOrder";
import {BasketService} from "../services/basket.service";
import {ProductService} from "./product.service";

@Injectable({
    providedIn: 'root'
})
export class ProductOrderService {
    constructor(private basketService: BasketService) {
    }

    public getOrders(): ProductOrder[] {
        let basket = this.basketService.getBasket();
        let orders = [];
        let order1 = new ProductOrder(1, "Irina", "Med", "+1234567",
            "med@mail.com", "IN_PROGRESS" , new Date(Date.now()), basket);//IN_PROGRESS, COMPLETE
        let order2 = new ProductOrder(2, "Victor", "Lol", "+2268567",
            "victor@mail.com", "IN_PROGRESS" , new Date(Date.now()), basket);//IN_PROGRESS, COMPLETE
        let order3 = new ProductOrder(3, "Oleg", "Smith", "+3274569",
            "olegS@mail.com", "IN_PROGRESS" , new Date(Date.now()), basket);//IN_PROGRESS, COMPLETE
        let order4 = new ProductOrder(4, "Michel", "Voronin", "+7004567",
            "MiVor@mail.com", "IN_PROGRESS" , new Date(Date.now()), basket);//IN_PROGRESS, COMPLETE
        let order5 = new ProductOrder(5, "Olga", "Smirnova", "+8297567",
            "OlgaSmir@mail.com", "COMPLETE" , new Date(Date.now()), basket);//IN_PROGRESS, COMPLETE
        let order6 = new ProductOrder(6, "Ivan", "Ivanov", "+5634567",
            "IvanI@mail.com", "IN_PROGRESS" , new Date(Date.now()), basket);//IN_PROGRESS, COMPLETE
        let order7 = new ProductOrder(7, "Sam", "Glog", "+4745767",
            "samsam@mail.com", "COMPLETE" , new Date(Date.now()), basket);//IN_PROGRESS, COMPLETE
        let order8 = new ProductOrder(8, "Peter", "Petrov", "+6200567",
            "petya@mail.com", "IN_PROGRESS" , new Date(Date.now()), basket);//IN_PROGRESS, COMPLETE
        let order9 = new ProductOrder(9, "Anna", "Last", "+7204567",
            "annushka@mail.com", "IN_PROGRESS" , new Date(Date.now()), basket);//IN_PROGRESS, COMPLETE
        let order10 = new ProductOrder(10, "Helga", "Parker", "+9222567",
            "parkerH@mail.com", "COMPLETE" , new Date(Date.now()), basket);//IN_PROGRESS, COMPLETE
        orders.push(order1);
        orders.push(order2);
        orders.push(order3);
        orders.push(order4);
        orders.push(order5);
        orders.push(order6);
        orders.push(order7);
        orders.push(order8);
        orders.push(order9);
        orders.push(order10);
        return orders;
    }
}
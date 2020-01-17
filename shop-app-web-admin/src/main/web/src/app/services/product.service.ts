import {Injectable} from '@angular/core';
import {Product} from "../models/product";

@Injectable({
    providedIn: 'root'
})
export class ProductService {
    constructor() {
    }

    public getProducts(): Product[] {
        let products = [];
        let product = new Product(1,'Ватрушка', 3, 'Пышная и вкусная ватрушечка',
            'https://i0.wp.com/russianrecipebook.com/wp-content/uploads/2012/04/Vatrushka1.jpg');
        let product2 = new Product(2,'Плюшка', 5, 'Плюшка с сахаром и любовью',
            'https://irecommend.ru/sites/default/files/product-images/136095/9_002.jpg');
        let product3 = new Product(3,'Сосиска в тесте', 1, 'Сочная сосиска из натурального мяса в ' +
            'сочетании с нежнейшим дрожжевым тестом',
            'http://tokyo-sushi.uxp.ru/wp-content/uploads/2019/09/%D0%A1%D0%BE%D1%81%D0%B8%D1%81%D0%BA%D0%B0.png');
        let product4 = new Product(4,'Расстегай с мясом', 5, 'Расстегай с говядиной и репчатым луком',
            'https://cdnd1x.arora.pro/upload/1c44f5da-45d4-4d73-b917-0440621794e9/original/374c34ca-fef0-4e14-b78d-a91a00e0c48a.jpg');
        let product5 = new Product(5,'Расстегай с горбушей', 5, 'Свежая красная рыбка в воздушном тесте',
            'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTr2fRzlBJL8BV7tvIbJyNGITD71ep9RtA99PWNkJfEtqd5nHYc&s');
        let product6 = new Product(6,'Пирог с луком и яйцом', 5, 'Большой пирог с луком и яйцом для всей семьи',
            'https://zira.uz/wp-content/uploads/2018/09/zalivnoy-pirog-2.jpg');
        let product7 = new Product(7,'Пирог с капустой', 5, 'Постный классический пирог с капустой',
            'https://www.patee.ru/r/x6/0d/c1/28/640m.jpg');
        let product8 = new Product(8,'Пирог с капустой и яйцом', 5, 'Сочная сытная начинка в сдобном тесте',
            'https://www.vashi-recepti.ru/wp-content/uploads/images/pirogvduxovkeskapustoyiyaytsom_58D56032.jpg');
        let product9 = new Product(9,'Крендель с сахаром', 5, 'Песочное тесто сладко тает во рту',
            'http://www.borisovhlebprom.by/images/data/catalog_602.jpg');
        let product10 = new Product(10,'Крендель с маком', 5, 'Песочное тесто в сочетании ' +
            'с сахаром и кондитерским маком',
            'http://irkcr.ru/files/products/204308056_9608f7f3f5817ac5b7acda6dab4d8520_800%5B2%5D.800x600.jpg');
        products.push(product);
        products.push(product2);
        products.push(product3);
        products.push(product4);
        products.push(product5);
        products.push(product6);
        products.push(product7);
        products.push(product8);
        products.push(product9);
        products.push(product10);
        return products;
    }
}

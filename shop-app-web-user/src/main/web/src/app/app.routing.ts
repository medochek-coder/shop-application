import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { ProductComponent } from './components/product/product.component';
import { BasketComponent } from "./components/basket/basket.component";
import { OrderComponent } from "./components/order/order.component";

const appRoutes: Routes = [
    { path: 'home', component: HomeComponent },
    { path: 'product/:id', component: ProductComponent },
    { path: 'basket', component: BasketComponent },
    { path: 'order', component: OrderComponent },
    { path: '**', redirectTo: 'home' }
];

export const routing = RouterModule.forRoot(appRoutes,  {useHash: true});

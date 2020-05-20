import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/client/home/home.component';
import { ProductComponent } from './components/client/product/product.component';
import { BasketComponent } from "./components/client/basket/basket.component";
import { OrderComponent } from "./components/client/order/order.component";
import { OrdersComponent } from "./components/admin/orders/orders.component";
import { OrderDetailsComponent } from "./components/admin/order-details/order-details.component";
import { HistoryComponent } from "./components/admin/history/history.component";
import { AhomeComponent } from "./components/admin/ahome/ahome.component";
import { AproductComponent } from "./components/admin/aproduct/aproduct.component";

const appRoutes: Routes = [
    { path: 'home', component: HomeComponent },
    { path: 'product/:id', component: ProductComponent },
    { path: 'basket', component: BasketComponent },
    { path: 'order', component: OrderComponent },
    { path: 'admin-home', component: AhomeComponent },
    { path: 'admin-orders', component: OrdersComponent },
    { path: 'admin-order/:id', component: OrderDetailsComponent },
    { path: 'admin-product/:id', component: AproductComponent },
    { path: 'admin-history', component: HistoryComponent },
    { path: '**', redirectTo: 'home' }
];

export const routing = RouterModule.forRoot(appRoutes,  {useHash: true});

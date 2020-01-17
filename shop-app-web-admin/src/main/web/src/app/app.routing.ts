import {Routes, RouterModule} from '@angular/router';
import {HomeComponent} from './components/home/home.component';
import {OrdersComponent} from "./components/orders/orders.component";
import {OrderDetailsComponent} from "./components/odred-details/order-details.component";
import {ProductComponent} from "./components/product/product.component";
import {HistoryComponent} from "./components/history/history.component";

const appRoutes: Routes = [
    { path: 'home', component: HomeComponent },
    { path: 'orders', component: OrdersComponent },
    { path: 'order/:id', component: OrderDetailsComponent },
    { path: 'product/:id', component: ProductComponent },
    { path: 'history', component: HistoryComponent },
    { path: '**', redirectTo: 'home' }
];

export const routing = RouterModule.forRoot(appRoutes,  {useHash: true});

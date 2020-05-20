import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { routing } from './app.routing';

import { MainUiModule } from './modules/main-ui/main-ui.module';

/*Services*/
import { ProductService } from "./services/product.service";
import { BasketService } from "./services/basket.service";
import {ProductOrderService} from "./services/productOrder.service";
import {SharedService} from "./services/shared.service";

/*Components*/
import { HomeComponent } from './components/client/home/home.component';
import { ProductComponent } from './components/client/product/product.component';
import { BasketComponent } from './components/client/basket/basket.component';
import { OrderComponent } from './components/client/order/order.component';
import { AhomeComponent } from './components/admin/ahome/ahome.component';
import { OrdersComponent } from "./components/admin/orders/orders.component";
import { OrderDetailsComponent } from "./components/admin/order-details/order-details.component";
import { AproductComponent } from "./components/admin/aproduct/aproduct.component";
import { HistoryComponent } from "./components/admin/history/history.component";

/*Popups*/
import {ActionPopup} from "./components/popup/pupup/action.popup";

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpClientModule,
        routing,
        BrowserAnimationsModule,
        MainUiModule
    ],
    declarations: [
        AppComponent,
        HomeComponent,
        AproductComponent,
        BasketComponent,
        OrderComponent,
        ProductComponent,
        AhomeComponent,
        OrdersComponent,
        OrderDetailsComponent,
        HistoryComponent,
        ActionPopup
    ],
    entryComponents: [
        ActionPopup
    ],
    providers: [
        HomeComponent,
        AproductComponent,
        BasketComponent,
        OrderComponent,
        ProductComponent,
        AhomeComponent,
        OrdersComponent,
        OrderDetailsComponent,
        HistoryComponent,
        ActionPopup,
        ProductService,
        BasketService,
        ProductOrderService,
        SharedService
    ],
    bootstrap: [AppComponent]
})

export class AppModule { }

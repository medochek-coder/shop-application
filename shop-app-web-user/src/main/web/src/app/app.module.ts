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

/*Components*/
import { HomeComponent } from './components/home/home.component';
import { ProductComponent } from './components/product/product.component';

/*Popups*/

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
        ProductComponent
    ],
    entryComponents: [
    ],
    providers: [
        HomeComponent,
        ProductComponent,
        ProductService
    ],
    bootstrap: [AppComponent]
})

export class AppModule { }

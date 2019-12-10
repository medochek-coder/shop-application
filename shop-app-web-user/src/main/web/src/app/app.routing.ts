﻿import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';

const appRoutes: Routes = [
    { path: 'home', component: HomeComponent },
    { path: '**', redirectTo: 'home' }
];

export const routing = RouterModule.forRoot(appRoutes,  {useHash: true});
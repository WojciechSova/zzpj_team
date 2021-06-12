import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { CookieService } from 'ngx-cookie-service';
import { AppComponent } from './app.component';
import { AuthInterceptor } from "./interceptors/auth-interceptor";
import { HTTP_INTERCEPTORS, HttpClientModule } from "@angular/common/http";
import { LoginComponent } from './login/login.component';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { AppRoutingModule } from "./app-routing.module";
import { MainComponent } from './main/main.component';
import { AccountsComponent } from './accounts/accounts.component';
import { AddAccountComponent } from './add-account/add-account.component';
import { EditUserComponent } from './edit-user/edit-user.component';
import { TransactionsComponent } from './transactions/transactions.component';

export const httpInterceptorProviders = [
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true},
];

@NgModule({
    declarations: [
        AppComponent,
        LoginComponent,
        MainComponent,
        AccountsComponent,
        AddAccountComponent,
        EditUserComponent,
        TransactionsComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        BrowserModule,
        HttpClientModule,
        FormsModule,
        ReactiveFormsModule,
    ],
    providers: [
        httpInterceptorProviders,
        CookieService,
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }

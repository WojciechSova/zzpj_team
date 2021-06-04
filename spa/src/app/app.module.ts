import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { CookieService } from 'ngx-cookie-service';
import { AppComponent } from './app.component';
import { AuthInterceptor } from "./interceptors/auth-interceptor";
import { HTTP_INTERCEPTORS } from "@angular/common/http";

export const httpInterceptorProviders = [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
];

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [
      httpInterceptorProviders,
      CookieService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
